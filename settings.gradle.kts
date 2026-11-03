pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "cipherkey"

include(":app")
include(":keyboard")

include(":core:common")
include(":core:crypto")
include(":core:messages")
include(":core:keys")
include(":core:sessions")

include(":data:database")
include(":data:repository")
include(":data:preferences")

include(":security:storage")
include(":security:clipboard")
include(":security:policies")

include(":plugins:api")
include(":plugins:registry")
