# 🔵 KotlinJoKenPo Firebase PVP ✌️✋✊
![image](https://github.com/user-attachments/assets/6a1f52a2-3498-44fb-89f2-c80fe5729616)

A Realtime Single or Multiplayer Jokenpo App designed to connect two devices with synced data for Multiplayer Game. The Application utilizes the free Realtime Database from Firebase integrating the updates from one player to another. 

![multiplayer](https://github.com/user-attachments/assets/75c0bf7a-4175-4cd3-92a6-463b3b87e237)

## Demonstration 🎁

https://github.com/user-attachments/assets/f861d066-e8cb-461d-81ad-378857535150

## UI Screenshot 📱

![1](https://github.com/user-attachments/assets/f83a1e2f-3ea5-4831-b833-50e852a01009)

![2](https://github.com/user-attachments/assets/35331a96-f18f-42f9-b947-0ad346a515fb)

![3](https://github.com/user-attachments/assets/a0e12215-bb99-42ad-93ec-5d89521f9c81)

![4](https://github.com/user-attachments/assets/f07d810e-3f4a-4c3b-b4c8-43deaab4ccdf)

![5](https://github.com/user-attachments/assets/d3447107-4e0b-4ea3-8d3a-23ea4d7796f5)

![6](https://github.com/user-attachments/assets/1340b3c2-4f1a-483c-9fa7-eb64185ff8d0)

![7](https://github.com/user-attachments/assets/b26e023e-45d1-4886-8ee6-af8caf7fa09f)

## Features 🛠️
- Realtime Multiplayer Gameplay: Connect and play with another player in real-time.
- Countdown Timer: A 5-second countdown to make choices.
- Choice Selection: Players can choose Rock, Paper, or Scissors.
- Result Display: Shows the game result after choices are made.
- Room Management: Create or join game rooms.
- Kotlin Coroutines: Supports asynchronous programming and smooth background task execution.
  
## Firebase Integration 🔥

🔥 Firebase Realtime Database: com.google.firebase:firebase-database
Allows for real-time data synchronization between devices. Updates from one player are instantly reflected to the other.

🔥 Firebase BoM (Bill of Materials): platform("com.google.firebase:firebase-bom:33.1.2")
Manages versions of Firebase libraries to ensure compatibility without specifying versions for individual Firebase dependencies.

🔥 Firebase Core: com.google.firebase:firebase-core:21.0.0
Essential for initializing Firebase services in the app.

🔥 Firebase Firestore: com.google.firebase:firebase-firestore-ktx
Provides cloud-based NoSQL database capabilities for more advanced data management.

## How It Works 🔄
## 1. Room Creation and Selection:

- Players can create a new room or join an existing one.
- Room management is handled via dialogs for room number input.

## 2. Player Status and Choices:

- The app tracks whether players are online and ready.
- Players make choices (Rock, Paper, Scissors) and the choices are updated in real-time.

## 3. Countdown Mechanism:

- A countdown timer starts once both players are ready.
- When the countdown finishes, the app determines the winner based on the players' choices.

## 4. Firebase Integration:

- Firebase Realtime Database is used to sync player statuses and choices.
- Updates from one player are reflected in real-time to the other player.

## Benefits 🌟
- Real-time Synchronization: Ensures that both players see the same game state simultaneously.
- Easy Room Management: Create or join rooms seamlessly with a user-friendly interface.
- Interactive Gameplay: Provides an engaging and competitive experience for users.

## Scripts 🚀
To get started with the KotlinJoKenPo app, follow these steps:

## Setup: 📝

💬 Clone the repository: git clone https://github.com/WesleyMaciel2510/KotlinJoKenPo
- Open the project in Android Studio.

💬 Configuration:
- Add your Firebase project credentials to the google-services.json file.
- Configure Firebase Realtime Database rules as needed.

🎨 Run the App:
- Build and run the app on your Android device or emulator.

▶️ Play the Game:
- Create or join a room and start playing Jokenpo with another player.


