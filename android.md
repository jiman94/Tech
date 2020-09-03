apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'
apply plugin: 'com.google.gms.google-services'
apply plugin: 'org.jetbrains.dokka-android'

android {
    dokka {
        outputFormat = 'javadoc'
        outputDirectory = "$buildDir/javadoc"
        includeNonPublic = true
    }

    signingConfigs {

        releaseConfig {
            storeFile file('pilotkeystore.jks')
            storePassword 'pilot'
            keyAlias 'pilot'
            keyPassword 'pilot'
        }
    }

    flavorDimensions "chicor"

    compileSdkVersion 29
    buildToolsVersion "29.0.3"
    defaultConfig {
        applicationId "com.app.pilot"

        minSdkVersion 22
        targetSdkVersion 29
        versionCode 10
        versionName "1.3.2"

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {

        debug {
        }

        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }

        buildTypes.each {
            it.buildConfigField("String", "NETWORK_TIMEOUT", '"' + 30 + '"')
        }
    }

    productFlavors {
        prd { //운영환경
            applicationId 'com.shinsegae.app.chicor'
            buildConfigField "String", "API_URL2", '"https://api2.pilot.com"'
            buildConfigField "String", "API_URL", '"http://api.pilot.com"'
            buildConfigField "String", "PAGE_URL", '"https://m.pilot.com"' // 운영서버

            buildConfigField "boolean", "SHOW_LOG", "false" // 실제 서비스할때는 false로 해야함.
            resValue "string", "app_name", "pilot"
            signingConfig signingConfigs.releaseConfig
            dimension "chicor"

            // 이미지가 안뜨는 경우 network_security_config.xml에 도메인을 추가해줘야한다.
            // 구글 배포시 usesCleartextTraffic는 false여야 한다. true값으로 올리면 앱 내려간다고 한다. 단, 현재 사용안함.
            // @see AndroidManifest.xml
            manifestPlaceholders = [networkSecurityConfig: "network_security_config"]
        }
        stg { // 개발테스트 - 스테이징
            // 현재 "선호상품 등록" 의 경우 api 도메인이 다르게 해야한다고 함
            applicationId 'com.shinsegae.app.chicor'
            buildConfigField "String", "API_URL2", '"http://api2.stg.pilot.co.kr"' // api 스테이징서버
            buildConfigField "String", "API_URL", '"http://api2.stg.pilot.co.kr"'
            buildConfigField "String", "PAGE_URL", '"http://m.stg.pilot.co.kr"' // 스테이징서버
            buildConfigField "boolean", "SHOW_LOG", "true"
            resValue "string", "app_name", "pilot_STG"
            dimension "chicor"
            manifestPlaceholders = [networkSecurityConfig: "network_security_config_stg"]
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        dataBinding = true
    }

    kotlinOptions {
        suppressWarnings = true
    }


}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).all {
    kotlinOptions.jvmTarget = '1.8'
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation project(':androidtagview')
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'androidx.core:core-ktx:1.3.1'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation project(path: ':fingerpush_3.1.5_chicor')
    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    implementation "com.google.code.gson:gson:$gson_version"

    implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"
    implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    kapt "com.android.databinding:compiler:3.1.4"


    // Koin for Kotlin
    implementation "org.koin:koin-core:$koin_version"
    // Koin extended & experimental features
    implementation "org.koin:koin-core-ext:$koin_version"
    // Koin for Unit tests
    api 'androidx.appcompat:appcompat:1.2.0'
    api 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation "org.koin:koin-test:$koin_version"


    // Koin AndroidX Scope features
    implementation "org.koin:koin-androidx-scope:$koin_version"
    // Koin AndroidX ViewModel features
    implementation "org.koin:koin-androidx-viewmodel:$koin_version"
    // Koin AndroidX Experimental features
    implementation "org.koin:koin-androidx-ext:$koin_version"

    // ANKO
    implementation "org.jetbrains.anko:anko-commons:$anko_version"
    implementation "org.jetbrains.anko:anko-sdk25:$anko_version"
    // sdk15, sdk19, sdk21, sdk23 are also available
    implementation "org.jetbrains.anko:anko-appcompat-v7:$anko_version"
    implementation "org.jetbrains.anko:anko-constraint-layout:$anko_version"
    implementation "org.jetbrains.anko:anko-sdk25-coroutines:$anko_version"

    // Activity Starter
    implementation "com.marcinmoskala.activitystarter:activitystarter:$activity_starter_version"
    implementation "com.marcinmoskala.activitystarter:activitystarter-kotlin:$activity_starter_version"
    kapt "com.marcinmoskala.activitystarter:activitystarter-compiler:$activity_starter_version"

    implementation 'org.jetbrains.kotlin:kotlin-reflect:1.3.72'
    //
    implementation 'com.google.android.material:material:1.3.0-alpha02'

    // Permission check lib
    implementation "com.karumi:dexter:$dexter_version"


    // Image processing(성능상의 이유로 glide 선택)
    implementation "com.github.bumptech.glide:glide:$glide_version"
    annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"


    // Sliding layer(푸터 영역 , 사이드 메뉴 슬라이드 애니메이션 뷰를 위함)
    implementation 'com.wunderlist:sliding-layer:1.2.5'

    // loop & Infinity view pager
    implementation 'com.github.MyoungsuJo:UIComponent:0.0.2'

    implementation 'com.github.safetysystemtechnology:android-shake-detector:v1.2'


    // BioMetric - FingerPrint
    implementation 'com.github.pwittchen:rxbiometric:0.1.0'

    // Tags view
    // 2020-03-25 기준 해당부분 직접 모듈로 삽입 하는것으로 변경(폰트 관련 문제)
//    implementation 'co.lujun:androidtagview:1.1.7'
    implementation 'androidx.appcompat:appcompat:1.2.0'


    // Easy Preference
    implementation 'com.pixplicity.easyprefs:library:1.9.0'


    // Firebase Analytics
    implementation "com.google.firebase:firebase-analytics:$firebase_analytics_version"
    implementation "com.google.firebase:firebase-core:$firebase_analytics_version"
    implementation "com.google.firebase:firebase-messaging:$firebase_messaging_version"

    // Circular ImageView
    implementation 'com.makeramen:roundedimageview:2.3.0'

    // Animation (with json file)
    implementation 'com.airbnb.android:lottie:3.4.1'

    // ImagePicker
    implementation 'com.github.nguyenhoanglam:ImagePicker:1.3.3'

    // PageIndicatorView
    implementation 'com.romandanylyk:pageindicatorview:1.0.3'

    // loopviewpager
    implementation 'com.asksira.android:loopingviewpager:1.3.1'

    // AppsFlyer
    implementation 'com.appsflyer:af-android-sdk:5.0.0'
    implementation 'com.android.installreferrer:installreferrer:1.1.2'


//    implementation 'com.squareup.picasso:picasso:2.71828'

    implementation([
            "com.squareup.retrofit2:retrofit:$retrofit_version",
            "com.squareup.retrofit2:adapter-rxjava2:$retrofit_version",
            "com.squareup.retrofit2:converter-gson:$retrofit_version",
            "com.squareup.okhttp3:okhttp:$okhttp_version",
            "com.squareup.okhttp3:logging-interceptor:$okhttp_version",
            // Coroutines for asynchronous calls (and Deferred’s adapter)
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version",
            // Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android'
            // 발생함...
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinx_coroutines_version",
            // Coroutines - Deferred adapter
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$retrofit_coroutines_version"

    ])


}


repositories {
    flatDir {
        dirs 'libs'
    }
}
