# Flexing :muscle: for Android

<p align="center">
    <h3 align="center">Flexing :muscle: for Android</h3> 
    <h4 align="center">
        <a href="https://bradmartin.net" target="_blank">by Brad Martin</a>
    </h4>
</p>

<p align="center">
    <a href="https://github.com/bradmartin/flexing/blob/master/src/LICENSE.md">
        <img src="https://img.shields.io/github/license/bradmartin/flexing.svg" alt="license" />
    </a>
    [![Release](https://jitpack.io/v/bradmartin/flexing.svg)]
    (https://jitpack.io/#bradmartin/flexing)
    <a href="https://paypal.me/bradwayne88">
        <img src="https://img.shields.io/badge/Donate-PayPal-green.svg" alt="donate">
    </a>
</p>

## Install

### Gradle:

- Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Add the dependency:

```groovy
dependencies {
    implementation 'com.github.bradmartin:flexing:0.1.30'
}
```

### Maven

- Add the JitPack repository to your build file:

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

- Add the dependency:

```xml
<dependency>
    <groupId>com.github.bradmartin</groupId>
    <artifactId>flexing</artifactId>
    <version>0.1.30</version>
</dependency>
```

### API

### ImagesKt Static Methods

| Method                                             | Description                        |
| -------------------------------------------------- | ---------------------------------- |
| _getBitmapFromImageView(ImageView: image)_: Bitmap | Returns a Bitmap from an ImageView |
