# Spring Boot and HTMX: The boring app

Start with the simplest app possible and evolve from there.

In this repo I implement a sample full stack app with Spring Boot and HTMX.
Apache Freemarker is used as a template language and Bootstrap as a CSS framework.

For complex interaction heavy use cases the sample includes a mechanism to render a vue app at a specific route as well.

The accompanying blog post can be read [in this repo](docs/article_part_1.md)
and [on my employers blog](https://www.codecentric.de/wissens-hub/blog/spring-boot-and-htmx-the-boring-app)

[![Java CI with Gradle](https://github.com/hendisantika/spring-boot-htmx/actions/workflows/gradle.yml/badge.svg)](https://github.com/hendisantika/spring-boot-htmx/actions/workflows/gradle.yml)

## Tech stack

| Component           | Version                                         |
|---------------------|-------------------------------------------------|
| Java (JDK)          | 25                                              |
| Kotlin              | 2.4.21                                          |
| Spring Boot         | 4.1.1                                           |
| Gradle (wrapper)    | 9.8.2                                           |
| HTMX (WebJar)       | 4.0.0                                           |
| Bootstrap (WebJar)  | 5.3.8                                           |
| WebJars locator     | webjars-locator-lite 1.1.5                      |
| AWS Lambda adapter  | aws-serverless-java-container-springboot4 3.0.4 |
| AWS Lambda runtime  | java25                                          |

## Prerequisites

- JDK 25 (e.g. [Eclipse Temurin](https://adoptium.net/)); Gradle toolchains will use it automatically
- Node.js + [pnpm](https://pnpm.io/) (only needed to work on the Vue app)
- [AWS SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html) (only needed to deploy)

## Build and test

```shell
./gradlew clean build
```

## Start the spring app with embedded vue app

Run `./gradlew clean bootRun` and open http://localhost:8080

Note: the build artifacts of the vue app are checked in at the correct places for your convenience.

## Start vue app in standalone mode

```shell
cd vue-app
pnpm install
pnpm run dev
```

open http://localhost:5173/

## Build a new version of the vue app to be included in the Spring Boot app

```shell
pnpm install
pnpm build
```

Note: The binary `rsync` has to be present in the PATH

## Deploy to AWS

This will build your lambda as jar and deploy it inside a standard java lambda runtime. For better boot times this will
also utilize the AWS SnapStart feature. Make sure to have
the [AWS sam cli](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html#install-sam-cli-instructions)
installed. Log into your target AWS account and then run:

```shell
sam build
sam deploy --guided
```

The app can be reached at the URL shown at the bottom of this command.
Be aware that the first request after ~15 Minutes of inactivity takes around 6 seconds to initialize the app again.
Subsequent requests should be fast.

## Continuous integration & dependency updates

- **GitHub Actions** (`.github/workflows/gradle.yml`) builds and tests the app on JDK 25 (Temurin) for every push and
  pull request to `main`, and submits the dependency graph so Dependabot alerts cover all Gradle dependencies.
- **Dependabot** (`.github/dependabot.yml`) keeps dependencies up to date:
  - **Gradle** – daily, with grouped PRs for Spring Boot, Kotlin, WebJars, AWS and other minor/patch updates
  - **npm / pnpm** (`vue-app`) – weekly, with grouped PRs for Vue and Vite/Vitest
  - **GitHub Actions** – weekly, batched into a single PR
  - PRs use conventional commit messages (`chore(deps): …`, `ci(deps): …`) and are labelled automatically
