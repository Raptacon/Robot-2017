////////////////////////////////////////////////////////////////////////////////
//
// File/Module Name : LCD_Range_Finder.ino
// Author           : Marty Otzenberger
// Creation Date    : 12/29/2014
// Project Name     : Lidar Lite
// Target Device    : Ardunio Mega 2560
// Tool Version     : Arduino 1.0.6
//

//////////////////////////////////////////////////////////////////////////////////
//#define DEBUG 1  //Define to enable serial output (PC)

////////////////////////////////////////////////////////////////////////////////
// Include Libraries
////////////////////////////////////////////////////////////////////////////////
#include <LiquidCrystal.h>  // Arduino's LCD library for Hitachi HD44780 driver.
#include <Wire.h>


// LIDAR Control
#define LIDARLite_ADDRESS 0x62  // Default I2C Address of LIDAR-Lite.
#define RegisterMeasure   0x00  // Register to write to initiate ranging.
#define MeasureValue      0x03  // Value to initiate ranging.
#define RegisterHighLowB  0x8f  // Register to get both High and Low bytes in 1 call.

#define LIDAR0EN 8
#define LIDAR1EN 9

////////////////////////////////////////////////////////////////////////////////
// Setup Function
////////////////////////////////////////////////////////////////////////////////
void setup(){
  // Set IO Directionality

  pinMode(LIDAR0EN,OUTPUT);
  digitalWrite(LIDAR0EN,LOW);

  pinMode(LIDAR1EN,OUTPUT);
  digitalWrite(LIDAR1EN,LOW);
  
  // Initialize LCD

  // Initialize I2C Bus
  Wire.begin();

  // If In Debug Mode Setup Serial Output To PC
  Serial.begin(115200);  //Setup Serial Interface
  #ifdef DEBUG
    Serial.begin(115200);  //Setup Serial Interface
    Serial.println("Setup Complete...");
  #endif
}


word doReading(int pin)
{
    //////////////////////////////////////////////////////////////////////////////
  // Declare Local Variables
  //////////////////////////////////////////////////////////////////////////////
  word  reading = 0x00;
  
  //////////////////////////////////////////////////////////////////////////////
  // Get Data From LIDAR
  //////////////////////////////////////////////////////////////////////////////
  // Setup Registers

  digitalWrite(pin,HIGH);
  delay(10);
  
  Wire.beginTransmission((int)LIDARLite_ADDRESS); // transmit to LIDAR-Lite
  Wire.write((int)RegisterMeasure);               // sets register pointer to  (0x00)  
  Wire.write((int)MeasureValue);                  // sets register pointer to  (0x00)  
  Wire.endTransmission();                         // stop transmitting
  #ifdef DEBUG
    Serial.println("Sent First Command Sequence...");
  #endif

  // Wait 5ms for transmit
  delay(10);
  
  // Setup More Registers
  Wire.beginTransmission((int)LIDARLite_ADDRESS); // transmit to LIDAR-Lite
  Wire.write((int)RegisterHighLowB);              // sets register pointer to (0x8f)
  Wire.endTransmission();                         // stop transmitting
  #ifdef DEBUG
    Serial.println("Sent Second Command Sequence...");
  #endif
  
  // Wait 5ms for transmit
  delay(10);

// Request Data
  Wire.requestFrom((int)LIDARLite_ADDRESS, 2);    // request 2 bytes from LIDAR-Lite
  #ifdef DEBUG
    Serial.println("Sent Data Request...");
  #endif

  // Wait For 2 Bytes Of Data
  if(2 <= Wire.available()){
    // Read In Data
    reading = Wire.read();   // receive high byte (overwrites previous reading)
    reading = reading << 8;  // shift high byte to be high 8 bits
    reading |= Wire.read();  // receive low byte as lower 8 bits
    #ifdef DEBUG
      Serial.print("Received Data: ");
      Serial.print(reading);
      Serial.println("...");
    #endif
  }

  digitalWrite(pin,LOW);

  delay(10);
  return reading;
  
}


////////////////////////////////////////////////////////////////////////////////
// Main Loop
////////////////////////////////////////////////////////////////////////////////
void loop(){

  
  // Format Value
  int reading0 = doReading(LIDAR0EN);
  float meters0 = reading0 / 100.0;
  int reading1 = doReading(LIDAR1EN);
  float meters1 = reading1 / 100.0;
  
  // Print Value
  #ifdef DEBUG
    Serial.print("Displaying Data: ");
    Serial.print(meters);
    Serial.println(" m...");
  #endif
  Serial.print("0=");
  Serial.print(meters0,2);
  Serial.print(" 1=");
  Serial.println(meters1,2);

  delay(20);
}

