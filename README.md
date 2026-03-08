<div align="center">

# AniyomiTV

### Aniyomi for Android TV — navigate with your remote

[![Nightly Release](https://img.shields.io/github/v/release/paul97ashish/aniyomi?include_prereleases&label=Nightly&labelColor=27303D&color=818cf8)](https://github.com/paul97ashish/aniyomi/releases/tag/nightly)
[![CI](https://img.shields.io/github/actions/workflow/status/paul97ashish/aniyomi/build_debug.yml?labelColor=27303D)](https://github.com/paul97ashish/aniyomi/actions)
[![License: Apache-2.0](https://img.shields.io/github/license/paul97ashish/aniyomi?labelColor=27303D&color=818cf8)](/LICENSE)

</div>

## What is this?

**AniyomiTV** is a fork of [Aniyomi](https://github.com/aniyomiorg/aniyomi) that adds Android TV support. It keeps the full original app experience but layers on TV-specific features so you can control it entirely with a TV remote.

## What's different from the original

| Feature | Detail |
|---|---|
| **D-pad cursor** | A visible arrow cursor appears on screen and moves with the TV remote's D-pad (↑ ↓ ← →). Accelerates when held. |
| **Remote tap** | Pressing the remote's `Enter / OK` button injects a tap at the cursor position — no touch needed. |
| **TV launcher icon** | The app now appears on the Android TV home screen (added `LEANBACK_LAUNCHER` intent filter). |
| **Cursor auto-hide** | The cursor fades out after 3 seconds of inactivity and reappears on the next D-pad event. |
| **Crash fix** | Fixed a startup crash on x86 emulators caused by `CookieManager` being initialized too early during dependency injection (`by lazy` fix in `AndroidCookieJar`). |

## Download

Every push to `main` automatically builds and publishes a new APK to the [Releases](https://github.com/paul97ashish/aniyomi/releases/tag/nightly) page.

**Requirements:** Android 8.0+ / Android TV 5.0+

## Installing on an Android TV emulator

1. Download `aniyomiTV-<version>-<sha>.apk` from [Releases](https://github.com/paul97ashish/aniyomi/releases/tag/nightly)
2. Run: `adb install -r aniyomiTV-<version>-<sha>.apk`
3. The app will appear on the TV home screen
4. Press any D-pad key to show the cursor

---

## Credits

This project is a fork of [Aniyomi](https://github.com/aniyomiorg/aniyomi) by the Aniyomi Open Source Project, which is itself based on [Mihon](https://github.com/mihonapp/mihon) and the original [Tachiyomi](https://github.com/tachiyomiorg/tachiyomi) by Javier Tomás.

All original features, extensions, trackers, and player capabilities are unchanged. Full credit goes to the original authors and contributors.

## License

```
Copyright © 2015 Javier Tomás
Copyright © 2024 Mihon Open Source Project
Copyright © 2024 Aniyomi Open Source Project

Licensed under the Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0
```
