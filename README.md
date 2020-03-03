Unit Pricing Tool
=================

| Category      | Measurement                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Pipeline      | [![Build](https://img.shields.io/circleci/project/github/ayltai/price-calculator/master.svg?style=flat)](https://circleci.com/gh/ayltai/price-calculator)                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Quality       | [![Code Quality](https://img.shields.io/codacy/grade/d874f2185ee342a1a4be0677167219c2.svg?style=flat)](https://app.codacy.com/app/AlanTai/price-calculator/dashboard) [![Sonar Quality Gate](https://img.shields.io/sonar/quality_gate/ayltai_price-calculator?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) [![Sonar Violations (short format)](https://img.shields.io/sonar/violations/ayltai_price-calculator?format=short&server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) |
| Coverage      | [![Sonar Test Success Rate](https://img.shields.io/sonar/test_success_density/ayltai_price-calculator?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) [![Code Coverage](https://img.shields.io/codecov/c/github/ayltai/price-calculator.svg?style=flat)](https://codecov.io/gh/ayltai/price-calculator) [![Sonar Coverage](https://img.shields.io/sonar/coverage/ayltai_price-calculator?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator)                                        |
| Ratings       | [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ayltai_price-calculator&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ayltai_price-calculator&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) [![Sonar Tech Debt](https://img.shields.io/sonar/tech_debt/ayltai_price-calculator?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) |
| Security      | [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ayltai_price-calculator&metric=security_rating)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ayltai_price-calculator&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=ayltai_price-calculator)                                                                                                                                                                                               |
| Miscellaneous | ![Maintenance](https://img.shields.io/maintenance/yes/2020) [![Android API](https://img.shields.io/badge/API-19%2B-blue.svg?style=flat&label=API&maxAge=300)](https://www.android.com/history/) [![Release](https://img.shields.io/github/release/ayltai/price-calculator.svg?style=flat)](https://1544-77390316-gh.circle-artifacts.com/0/apk/app-release.apk) [![License](https://img.shields.io/github/license/ayltai/price-calculator.svg?style=flat)](https://github.com/ayltai/price-calculator/blob/master/LICENSE)                                                        |

The forgotten shopping tool - using unit pricing is the [best](http://eprints.qut.edu.au/96291/) way for shoppers to save money on grocery shopping!

[![Buy me a coffee](https://img.shields.io/static/v1?label=Buy%20me%20a&message=coffee&color=important&style=for-the-badge&logo=buy-me-a-coffee&logoColor=white)](https://buymeacoff.ee/ayltai)

![Light mode](design/screenshot-1.png "Light mode") ![Dark mode](design/screenshot-2.png "Dark mode")

![Unit picker](design/screenshot-3.png "Unit picker") ![Unit type picker](design/screenshot-4.png "Unit type picker")

## Features
* Identify the cheapest item
* Show percentage difference for all items
* Supported unit types: volume, weight, length, area, time
* Automatic unit conversion
* Support dark mode

## Supported units
| Type   | Unit                                                             |
|--------|------------------------------------------------------------------|
| Weight | milligram, gram, kilogram, pound, ounce, tonne                   |
| Volume | millilitre, litre, ounce, gallon                                 |
| Length | millimetre, centimetre, metre, kilometre, inch, foot, yard, mile |
| Time   | minute, hour, day, week, month, year                             |
| Area   | sq. metre, sq. kilometre, sq. inch, sq. foot, sq. yard, sq. mile |

## Requirements
This app supports Android 4.4 Jelly Bean (API 19) or later.

## Acknowledgements
This app is made with the support of open source projects:

* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Dagger 2](https://google.github.io/dagger)
* [Espresso](https://google.github.io/android-testing-support-library)
* [JUnit 4](https://github.com/junit-team/junit4)
* [Robolectric](http://robolectric.org)
* [LeakCanary](https://github.com/square/leakcanary)
* [Dexcount Gradle Plugin](https://github.com/KeepSafe/dexcount-gradle-plugin)

… and closed source services:

* [CircleCI](https://circleci.com)
* [SonarCloud](https://sonarcloud.io)
* [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics)
