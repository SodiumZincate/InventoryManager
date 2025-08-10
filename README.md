Inventory Logging App
An Android app built in Java to manage component logs using Room database and RecyclerView. The app allows you to create, view, update, and delete logs for inventory items, with separate menus for finished, unfinished, and all items.

Features
Add new logs with details such as:

Component name

Time taken

Return date

Taken by

View logs filtered into:

Finished items

Unfinished items

All items

Mark logs as finished

Delete logs from the database

Data persistence using Room database

Smooth scrolling list with RecyclerView

Tech Stack
Language: Java

Database: Room (SQLite)

UI: XML layouts, RecyclerView

Architecture: MVVM (Model-View-ViewModel)

Installation
Clone the repository:

bash
Copy
Edit
git clone <repository_url>
Open the project in Android Studio.

Let Gradle sync automatically.

Run the app on an emulator or physical device.

Usage
Add a new log: Fill in the form and save.

View logs: Switch between Finished, Unfinished, and All logs.

Mark as finished: Tap the finish button in the log list.

Delete a log: Tap the delete button in the log list.

GitHub Repository
The complete source code is available on GitHub:
GitHub Repository Link
