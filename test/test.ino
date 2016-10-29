#include <CurieIMU.h>

#define THRESHOLD 0.1
#define MAXOBS 200

int lastobs[3][5] = {
  {0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0}
};

int obs[3][MAXOBS];

int xRaw, yRaw, zRaw, count = 0, d = 50, p=0;
bool lastState = false;


void storeObs(int x, int y, int z) {
  
  obs[0][p] = x; obs[1][p] = y; obs[2][p] = z;
  //XXX: not sure if wraparound is a good idea
  p = (p+1) % MAXOBS;
}

bool isStatic(int x, int y, int z, int pos) {

  int ix = 0, iy = 0, iz = 0;

  for(int i=0; i<5; i++) {
      ix += lastobs[0][pos]; iy += lastobs[1][pos]; iz += lastobs[2][pos];
  }
  
  lastobs[0][pos] = x; lastobs[1][pos] = y; lastobs[2][pos] = z; 

  double axL = ix/5*(1-THRESHOLD), ayL = iy/5*(1-THRESHOLD), azL = iz/5*(1-THRESHOLD), axH = ix/5*(1+THRESHOLD), ayH = iy/5*(1+THRESHOLD), azH = iz/5*(1+THRESHOLD);

  return (axL <= x && x <= axH) && (ayL <= y && y <= ayH) && (azL <= z && z <= azH);
}


void setup() {
  Serial.begin(9600); // initialize Serial communication
  while (!Serial);    // wait for the serial port to open

  
  // initialize device
  Serial.println("Initializing IMU device...");
  CurieIMU.begin();

  // Set the accelerometer range to 2G
  CurieIMU.setAccelerometerRange(2);

  //make sure we don;t get any rubbish
  int xRaw = 0, yRaw = 0, zRaw = 0;

  while(xRaw == 0 || yRaw == 0 || zRaw == 0) {
    CurieIMU.readAccelerometer(xRaw, yRaw, zRaw);
    delay(5);
  }

  xRaw = unSign(xRaw); yRaw = unSign(yRaw); xRaw = unSign(xRaw);

  for(int i=0; i<5; i++) {
    isStatic(xRaw, yRaw, zRaw, i);
  }

  
}

int unSign(int x) {
  return x<0?-x:x;
}

//need to see if averaging retains enough information
byte getAverageSample(byte samples[], unsigned int num, unsigned int pos,
                   unsigned int step)
{
    unsigned int ret;
    unsigned int size = step * 2;

    if (pos < (step * 3) || pos > (num * 3) - (step * 3)) {
        ret = samples[pos];
    } else {
        ret = 0;
        pos -= (step * 3);
        for (unsigned int i = 0; i < size; ++i) {
            ret += samples[pos - (3 * i)];
        }

        ret /= size;
    }

    return (byte)ret;
}

//
//void downsample(int samples[][], int numSamples, byte vector[])
//{
//    //how much to average
//    int cm = numSa
//
//    for(int i=0; i<numSamples; i++) {
//
//      
//    }
//    
//    //we can use at most 42 samples
//    unsigned int vi = 0;
//    unsigned int si = 0;
//    unsigned int step = numSamples / samplesPerVector;
//    unsigned int remainder = numSamples - (step * samplesPerVector);
//
//    /* Centre sample window */
//    samples += (remainder / 2) * 3;
//    for (unsigned int i = 0; i < samplesPerVector; ++i) {
//        for (unsigned int j = 0; j < 3; ++j) {
//            vector[vi + j] = getAverageSample(samples, numSamples, si + j, step);
//        }
//
//        si += (step * 3);
//        vi += 3;
//    }
//}

void loop() {

  CurieIMU.readAccelerometer(xRaw, yRaw, zRaw);

  xRaw = unSign(xRaw); yRaw = unSign(yRaw); xRaw = unSign(xRaw); 

  bool is = isStatic(xRaw, yRaw, zRaw, count);

  if(is) {
    Serial.println("static");
  }
  
  if(is && !lastState) {
    //move ends: 
    //- wrap up
    //- down sample
    //- classify
    //- send out

    

    p = 0;
  } else if (!is) {
    //not static so store reading
    storeObs(xRaw, yRaw, zRaw);    
  }

  //if(is == lastState) { d = 500; } else { d = 100; } 
  d = 50;
  
  delay(d);

  Serial.print(xRaw / 100);
  Serial.print(" ");
  Serial.print(yRaw / 100);
  Serial.print(" ");
  Serial.print(zRaw / 100);
  Serial.println();

  count = (count + 1) % 5;
  lastState = is; 
}

