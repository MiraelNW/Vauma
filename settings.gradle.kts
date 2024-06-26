pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }
}

rootProject.name = "Vauma"

include ("vauma")

include(":features:account")
include(":features:detailInfo")
include(":features:favourites")
include(":features:forgotpassword")
include(":features:home")
include(":features:search")
include(":features:signin")
include(":features:signup")
include(":features:video")

include(":core:designsystem")
include(":core:extensions")
include(":core:models")
include(":core:utils")
include(":core:theme")

include(":data:storeclient")
include(":data:storeclient:api")
include(":data:storeclient:impl")
include(":data:network")
include(":data:network:api")
include(":data:network:impl")
include(":data:database")
include(":data:database:api")
include(":data:database:impl")
include(":data:repositories")
include(":data:repositories:api")
include(":data:repositories:impl")

include(":utils:logger")
include(":utils:imageloader:api")
include(":utils:imageloader:impl")
