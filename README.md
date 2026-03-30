<div align="center">

<img src="https://raw.githubusercontent.com/aniyomiorg/aniyomi/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp" alt="AniyomiTV" width="100"/>

# AniyomiTV

### Full-featured anime & manga manager — built for Android TV

[![Latest Release](https://img.shields.io/github/v/release/paul97ashish/aniyomi?label=Release&labelColor=27303D&color=818cf8)](https://github.com/paul97ashish/aniyomi/releases/latest)
[![Nightly](https://img.shields.io/github/v/release/paul97ashish/aniyomi?include_prereleases&label=Nightly&labelColor=27303D&color=6366f1)](https://github.com/paul97ashish/aniyomi/releases/tag/nightly)
[![Build](https://img.shields.io/github/actions/workflow/status/paul97ashish/aniyomi/build_debug.yml?labelColor=27303D&label=CI)](https://github.com/paul97ashish/aniyomi/actions)
[![License](https://img.shields.io/github/license/paul97ashish/aniyomi?labelColor=27303D&color=818cf8)](LICENSE)

</div>

---

## What is AniyomiTV?

**AniyomiTV** is a fork of [Aniyomi](https://github.com/aniyomiorg/aniyomi) with full Android TV support. It keeps everything that makes Aniyomi great — extensions, trackers, the MPV-based player — and adds a complete D-pad navigation experience so you can control the app entirely with a TV remote.

No cursor. No mouse emulation. Proper Compose focus navigation.

---

## TV Features

| Feature | Description |
|---|---|
| **D-pad navigation** | Full Up/Down/Left/Right focus traversal across every screen — library, browse, settings, episode list, player controls |
| **Focus highlight** | White rounded border + subtle scale animation shows exactly which item is selected |
| **NavigationRail layout** | TV devices automatically use the tablet sidebar layout — much better than a bottom nav bar on a 10-foot display |
| **Player D-pad** | Seek forward/back when controls are hidden; navigate buttons when controls are shown |
| **TV launcher icon** | App appears on the Android TV home screen (`LEANBACK_LAUNCHER`) |
| **Overscan padding** | Adjustable screen padding (0–48 dp) in Settings → Appearance for TVs with overscan |
| **Pager lock** | Horizontal swipe between Browse tabs is disabled on TV — no accidental page switches |

---

## Download

| Channel | Link | Notes |
|---|---|---|
| **Stable** | [Latest Release](https://github.com/paul97ashish/aniyomi/releases/latest) | Tagged releases with changelog |
| **Nightly** | [Nightly Release](https://github.com/paul97ashish/aniyomi/releases/tag/nightly) | Built on every push to `main` |

**Requirements:** Android 8.0+ · Android TV / Google TV 5.0+

### Install via ADB

```bash
adb install -r aniyomiTV-<version>.apk
```

The app will appear on the TV home screen after installation. Enable **Install from unknown sources** if prompted.

---

## What's unchanged from Aniyomi

Everything else is stock Aniyomi:

- All anime & manga extensions
- Tracking (AniList, MyAnimeList, Kitsu, …)
- MPV-based video player with subtitle support
- Download manager, library, history, updates

---

## Building from source

```bash
git clone https://github.com/paul97ashish/aniyomi.git
cd aniyomi
./gradlew app:assemblePreview   # debug-signed, installable
```

Requires JDK 17.

---

## Support the project

If you find AniyomiTV useful, consider sending a tip — it helps keep the project going.

**Crypto (EVM / Smart Wallet):** `0x54eB96316E7D5978D58B2895f41E14F7345A46CF`

---

## Credits

AniyomiTV is a fork of [Aniyomi](https://github.com/aniyomiorg/aniyomi) by the Aniyomi Open Source Project, which is itself built on [Mihon](https://github.com/mihonapp/mihon) and the original [Tachiyomi](https://github.com/tachiyomiorg/tachiyomi) by Javier Tomás.

All credit for the core app, extensions ecosystem, and player goes to the original authors and contributors.

---

## License

```
Copyright © 2015 Javier Tomás
Copyright © 2024 Mihon Open Source Project
Copyright © 2024 Aniyomi Open Source Project

Licensed under the Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0
```
