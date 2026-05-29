# IoT Automated Fire Extinguisher & Monitoring System

An IoT-based automated fire monitoring and directed extinguishing system. Features real-time environmental tracking and flame positioning using ESP32, multi-sensor fusion (Flame, MQ-2, DHT11), and a Pan-Tilt servo mechanism. Fully integrated with an Android application via Firebase for remote monitoring, manual override, and push notifications.

## 🚀 Features
- **Early Detection:** Uses a multi-sensor array (Temperature, Humidity, Smoke/Gas) for instant fire hazard identification.
- **Flame Positioning:** Employs a 5-way infrared sensor to locate the fire source's coordinates.
- **Directed Extinguishing:** Controls a Pan-Tilt mechanism (X and Y axes) to aim the water nozzle directly at the fire.
- **Real-time Monitoring:** Synchronizes environmental data and system status to an Android app via Firebase.
- **Manual Override:** Allows users to manually control the water pump and servo direction via a mobile joystick.
- **Smart Alerts:** Instant push notifications (FCM) and emergency dialogs when fire is detected.
- **Historical Data:** Visualizes fire events using lists and charts (MPAndroidChart).

## 🛠 Tech Stack
### Hardware
- **MCU:** ESP32 (Dual-core, Integrated Wi-Fi).
- **Sensors:** 
  - 5-Way Flame Sensor (Infrared).
  - MQ-2 (Gas & Smoke).
  - DHT11 (Temperature & Humidity).
- **Actuators:**
  - 2x MG90S Servos (Pan-Tilt mechanism).
  - 5V Mini Submersible Pump.
  - 5V Active-HIGH Relay.
  - Buzzer for local alarm.

### Software
- **Mobile App:** Android Java (Android Studio).
- **Backend:** Firebase Realtime Database (RTDB) & Firebase Cloud Messaging (FCM).
- **Firmware:** Arduino IDE / C++ for ESP32.
- **Protocol:** NTP for precise event timestamping.

## 📊 System Architecture
The system consists of 4 main functional blocks:
1. **Sensor Block:** Collects physical data (Temp, Smoke, Infrared).
2. **Central Controller (ESP32):** Processes logic, navigation algorithms, and communication.
3. **Warning Block:** Local buzzer and remote Android notifications.
4. **Actuator Block:** Pan-Tilt servos and water pump.

## 📈 Performance
- **Response Time:** Fire detection and pump activation in < 1.2s.
- **Control Latency:** ~0.5s manual override delay under stable Wi-Fi.
- **Reliability:** ESP32 operates independently even if the internet is lost to ensure safety.

## 📸 Screenshots
- **Dashboard:** Real-time monitoring of sensors.
- **Manual Mode:** Remote joystick for servo control.
- **Fire History:** Data visualization of past events.
