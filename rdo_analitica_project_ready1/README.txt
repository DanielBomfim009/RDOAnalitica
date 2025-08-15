RDO ANALITICA - Android project (Kotlin) - READY
--------------------------------------
Location: open the folder 'RDOAnalitica' in Android Studio.

What was added/updated:
- Adaptive icon (ic_launcher foreground/background + XML) for modern launchers.
- Date picker for date field, time pickers for time fields.
- Required-number validation for the main 'Número' field.
- SharedPreferences draft autosave (fields persist between app launches).
- Improved input types and hints.
- Send button clears local draft on successful POST to Google Forms.

Important: I cannot compile the APK inside this environment. To produce an installable APK:
  1. Open the project in Android Studio (File -> Open -> select RDOAnalitica).
  2. Let Gradle sync. If prompted, install recommended SDK/Build Tools (compileSdk 34).
  3. Run Build -> Build Bundle(s) / APK(s) -> Build APK(s). Android Studio will produce a debug APK you can install.
  4. Alternatively, connect a device and press Run to install directly.

Google Forms POST:
  Endpoint: https://docs.google.com/forms/d/e/1FAIpQLScDh78CnfCBkflyH2IlcYjr1h1il5hp3iZ4wmrc5jUHjHYVQA/formResponse
  The app sends entries as "entry.<number>" where <number> equals the ID used as EditText field names in code.

If you want, I can:
 - Generate and include a debug-signed APK (requires Android SDK/build tools which aren't available here).
 - Make UI polish: colors, fonts, icons, tooltips, additional validations, RTL support, etc.
