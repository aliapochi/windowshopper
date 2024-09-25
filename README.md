

# Window Shopper App

Window Shopper is a shopping wishlist app where users can browse products, swipe to save items to a wishlist, and share their list with friends. The app features a simple and user-friendly interface designed for easy product exploration.

## Features
- **Product Swiping**: Swipe right to save products to your wishlist, and swipe left to discard them.
- **Wishlist**: View all saved products in a dedicated wishlist.
- **Share Wishlist**: Send your wishlist to friends via sharing options.
- **Offline Support**: The app caches products for offline use.
- **User Registration**: Create an account and log in to save your preferences.


## Technologies Used
- **Kotlin**: Core language for Android development.
- **Jetpack Compose**: UI toolkit for building modern Android interfaces.
- **MVVM Architecture**: Following the Model-View-ViewModel pattern for a clean architecture.
- **Room Database**: Local database for caching and data persistence.
- **Retrofit**: For networking and fetching product data from a remote server.
- **Hilt**: Dependency Injection framework for managing app components.
- **Coroutines**: For asynchronous data handling.
- **Firebase Authentication**: For user registration and login.
- **WorkManager**: For handling background tasks like syncing data.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/aliapochi/windowshopper.git
   ```

2. Open the project in Android Studio.

3. Build the project and run the app on an emulator or physical device.

## How to Use

1. **Browse Products**: Open the app and start browsing products. Swipe right to save or swipe left to discard.
2. **View Wishlist**: Tap on the wishlist icon to view all saved items.
3. **Share Wishlist**: From the wishlist, tap the share button to send the list to friends.
4. **Offline Browsing**: Saved items are accessible even when offline.

## App Structure

- **View Layer**: UI built using Jetpack Compose.
- **ViewModel Layer**: Handles business logic and interacts with the repository.
- **Repository Layer**: Manages data sources (Room Database and Retrofit for network).
- **Database**: Room database used for caching product data locally.
- **Retrofit Service**: Used to fetch product data from the backend.

## Project Structure

```
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── com.example.windowshopper
│   │   │   │   │   ├── di    # Dependency injection
│   │   │   │   │   ├── ui    # UI components
│   │   │   │   │   ├── data  # Models and data handling
│   │   │   │   │   ├── repository  # Data repository classes
│   │   │   │   │   ├── network  # Retrofit services
│   │   │   │   │   ├── database  # Room database classes
│   │   │   │   │   └── viewmodel  # ViewModels for each screen
│   │   │   ├── res
│   │   │   │   ├── layout  # Layout files
│   │   │   │   ├── drawable  # Images and icons
```

## Contributing
Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a Pull Request.

## License
This project is licensed under the MIT License.

