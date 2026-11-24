# StationsApp

A read-only Android app for browsing stations and their devices. Tap a station to see its name, address, and each device's name, type, and status.

## Build and run

Use Android Studio with Android SDK Platform 37 installed and JDK 21 selected as the Gradle JDK. The app runs on devices and emulators with Android 7.0 (API 24) or newer.

1. Clone the repository and open its root in Android Studio.
2. Sync Gradle, select the `app` run configuration, and run it on a device or emulator.
3. Start online so the app can fetch the station list and populate the cache.

## Try the app

- Open a station with devices while online. Go back, enable airplane mode, then reopen the same station. The saved station and devices should still appear with an error message and **Retry**. Reconnect and tap **Retry** to refresh.
- Open a station whose device request returns an empty list. The detail screen shows **No devices available**.

The mock backend deliberately simulates slow responses, timeouts, and server errors. If the first request fails before anything has been cached, reconnect if needed and tap **Retry**. Devices are cached per station after a successful detail request.

## Architecture

- **Presentation** uses Jetpack Compose, Navigation Compose, and ViewModels that expose screen state with `StateFlow`.
- **Domain** defines the models, repository interface, and use cases for reading the cache and refreshing it.
- **Data** uses Ktor with OkHttp and Kotlin serialization for network requests, and Room for local storage. Koin wires the dependencies together.

Both screens read from Room. A successful refresh updates the database in a transaction. A successful empty response clears the relevant cached rows, and a failed refresh keeps the saved data available.

See [IMPROVEMENTS.md](IMPROVEMENTS.md) for the next steps I would prioritize.
