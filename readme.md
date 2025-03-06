# Movie Explorer

![Kover Coverage](https://img.shields.io/badge/Kover_Coverage-99.19%25-brightgreen?logo=kotlin)

(Not considering UI testing)

## Description
Movie Explorer is an Android app that displays a list of **now playing**, **popular**, and **upcoming** movies fetched from [The Movie Database (TMDb) API](https://developer.themoviedb.org/reference/movie-now-playing-list). Users can view detailed information about each movie by clicking on it. The app is built using modern Android development practices, including **MVVM architecture**, **Jetpack Compose**, **Coroutines**, and comprehensive **unit testing**.

---

## Features
- **List Screen**:
    - Three tabs for **Now Playing**, **Popular**, and **Upcoming** movies.
    - Horizontal lists displaying movie posters, and titles.
- **Detail Screen**:
    - Comprehensive movie details, including overview and genres.
    - Back navigation to the list screen.
- **Error Handling**:
    - Graceful handling of network errors and API failures.
    - Informative error messages for users.
- **Caching**:
    - Local caching of movie data to minimize network requests.
- **Modern Architecture**:
    - Built using **MVVM** architecture.
    - **Jetpack Compose** for a declarative and modern UI.
    - **Coroutines** for asynchronous operations.
- **Unit Testing**:
    - Comprehensive unit tests for domain logic, data retrieval, and UI interactions.

---

## Usage
1. **List Screen**:
    - Swipe between the **Now Playing**, **Popular**, and **Upcoming** tabs to view movies.
    - Scroll horizontally to browse movies in each category.
2. **Detail Screen**:
    - Click on a movie to view its details, including overview, genres, and runtime.
    - Use the back button to return to the list screen.

---

## Architecture
The app follows the **MVVM** architecture pattern, ensuring a clear separation of concerns and a unidirectional data flow. Key components include:
- **Model**: Represents the state of the app (e.g., movie data, loading state).
- **View**: Built using **Jetpack Compose** for a modern and declarative UI.
- **ViewModel**: Represents user actions (e.g., clicking a movie, refreshing the list).

### Tech Stack
- **Jetpack Compose**: For building the UI.
- **Coroutines**: For asynchronous operations like network requests.
- **Retrofit**: For API communication with TMDb.
- **Dagger Hilt**: For dependency injection.
- **Jetpack Navigation**: For seamless navigation between screens.
- **Coil**: For image loading.
- **Mockito & JUnit**: For unit testing.
- **Kover**: For test coverage.
- **Chucker**: For observing api calls.
- **netmock**: For testing API.
- **Konsis**: For architectural testing.

---

## Installation
To run the app locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://https://github.com/arashad94/Banque-Misr-Task.git

2. **Open the project in Android Studio**
3. **Run**
