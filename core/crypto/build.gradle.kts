plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(project(":core:common"))

    // BouncyCastle is used ONLY through its lightweight API (Argon2id key
    // derivation). No JCA provider insertion is performed, so the Android
    // platform default cryptographic providers are never overridden.
    api(libs.bouncycastle.provider)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
