
# ArtistArchivist

This is my first completely Compose app, an artist finder, that was built for DICE and powered by MusicBrainz API.

###### Light Mode

<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/585aca1d-6d79-4648-b9c7-9c2c45d85687" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/9c65bca5-d90b-4d6b-96c8-1d76b0518e27" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/1e43ed8a-f73b-49e7-85bf-7aa640020fd7" width="240">


###### Dark mode
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/c9cad9c4-a2d5-4b8c-971e-1b02296b6411" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/23123bf6-8ab0-43d2-b8b4-4468aaebd08b" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/f76e2487-3064-41fd-8dc0-52ac046715d9" width="240">

###### Dynamic theme
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/8e3c838d-05f4-465d-ad8a-38d2b8f7e0f5" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/164a698c-623e-4c5d-8713-3ca939dbff52" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/7d990017-d4ac-443b-be0c-71165fb251aa" width="240">
<img src="https://github.com/annamatolay/ArtistArchivist/assets/19217964/063902ca-6331-4d4b-a52f-7625b13eb19f" width="240">

## Features & Dependencies

- Compose and Coroutines with MD3, Material You and Dark Theme support
- Custom Foundation styled fonts, thanks to [rsperberg](https://github.com/rsperberg) ❤️
  - [Foundation Title Hand](https://github.com/rsperberg/foundation-titles-hand)
  - [Foundation Logo](https://github.com/rsperberg/foundation-logo)
- DI with [Koin](https://github.com/InsertKoinIO/koin) (Compose and Annotation)
- Logging by [Timber](https://github.com/JakeWharton/timber)
- Networking with Retrofit2 and OKHttp3 by [Square](https://github.com/square)
- JSON serialization with [Moashi](https://github.com/square/moshi)
- Semi-configured build types with a unique app ID and app name

## Disclaimer

This section is all about what could have been if I don't get stuck with Compose or Coroutines, prioritized better, or don't get sick... **However, I'm very grateful for this task because I finally could truly deep dive into Compose and try Coroutines for the first time.**

Modularisation and testing are not finished. In both of the cases, I have more progress than I pushed here but didn't want to share broken code. However, I feel I need to share my thoughts.

#### Version controll 

I followed [Conventional Commits](https://cheatography.com/albelop/cheat-sheets/conventional-commits/), but if you check the history I basically pushed most of my work in one commit. I never do something like that if I'm working in a team. But even if I'm working on a pet project. This is an exception. If you interested how I mannaging version controll I suggest you to check the [commit history](https://github.com/annamatolay/lirael-android/commits/main) of my most recent pet project.

#### Modularisation 

I already organized my packages like how I would create the modules from them.

These would have been my modules:

`core`: contains everything that the app is built on like networking, logging, custom Compose components, and so on. `model` should be brake down into smaller data classes and move into their own feature module.

`feature:details` and `feature:search`: I planned to create a module for every feature with a contract module as well and make each feature module only accessible by its contract module. E.g. I should have `feature:search` and `feature-contract:search` and `app` module would only access to it by the contract module.  You can find two unused, empty interfaces because of that. `Contract` interfaces should be in a feature-contract module that is called from outside and its feature module should contain the implementation of it. That can be done with the feature's usecases, repositories, and so on.

`util`: Contains extension functions, mocks, and constants that are needed all across the project (not only for testing but for previews too).

`ui`: Everything UI-related that is common. Compose components should be moved here as well.

`app`: Home of `Application` and `MainActivity` and wiring up all the different modules, but basically would contains "almost nothing".

### Known Issue

Occasional crashes when the app is in the background. It's throwing `java.lang.IllegalStateException: Check failed.` because of how I'm handling saved states for Compose views. I tried to figure it out, but couldn't so far. I mostly got suggestions on how to handle Coroutine dispatcher, but I'm already doing it like that. The issue is also not consistently reproducible too.

### Lesson learned

1. **Start with way simpler UI, especially if the framework relatively new.**<p>
I spent too much with animating my views and the end result was nearly as satisfying.<br>
I got really stuck with the different Bottom sheets. First I tried the `BottomSheetScaffold` which is quite difficult to hide. Then went with `ModalBottomSheet` which I also failed to successfully implement. After all, I basically created a card on top of everything that was way easier. I should have gone with that first and made it fancier if I have time.

2. **Write tests frequently, even if it's a small project.**<p>
One of my strongest is testing. During all my previous job I had done at least one outstanding change regarding testing (first team to having automated tests with CI/CD flow, laying down Android UI testing, or introducing a better alternative for Espresso).<br>
And now here I am with basically nothing (and some broken UI tests locally) because I spent too much on UI implementation and also Compose testing is a bit different.<br>
I could at least write some unit tests for the usecase, repository, etc.


4. **Don't add more new libraries that you are not familiar with if you already have new things on your plate.**<p>
I did Koin with Annotations the first time because I thought it was fun and more similar to Dagger (from the outside). It was fun, but also I got a little difficulty with that. That's usually okay, but not when I'm already behind my schedule and actually need to write UI in a completely new way and use something for async communication that I only now from articles.

---

Thank you for reading that and I hope somewhat you liked my solution at least. I don't, but at least learned a a lot and will complete this project regardless of the outcome of my submission (I won't push any changes until I get a review from you).
