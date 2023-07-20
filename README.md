<p align="center">
  <img src="https://vue.ai/images/logo/vue-logo-light.svg" alt="VueSDK for Android" width=300/>
</p>

# Table of Contents

<!-- MarkdownTOC -->
- [Table of Contents](#table-of-contents)
- [Overview](#overview)
- [Quick Start Guide](#quick-start-guide)
  - [Install Vue SDK](#install-vue-sdk)
    - [Using Android Studio](#using-android-studio)
  - [Initialize VueSDK](#initialize-vuesdk)
  - [Discover Events](#discover-events)
  - [Track Event](#track-event)
  - [Get Recommendations](#get-recommendations)
    - [Get Recommendations by Page](#get-recommendations-by-page)
    - [Get Recommendations by Module](#get-recommendations-by-module)
    - [Get Recommendations by Strategy](#get-recommendations-by-strategy)
  - [Set User](#set-user)
  - [Reset User](#reset-user)
  - [VueSDK debugging and logging](#vuesdk-debugging-and-logging)
  - [Complete Code Example](#complete-code-example)
  - [I want to know more!](#i-want-to-know-more)
<!-- /MarkdownTOC -->


# Overview

VueSDK is a library/SDK providing all the functions and utilities required to help you build and integrate all the recommendations and search offered by Vue.AI. To know about the detailed set of use cases available - please [visit this link](https://support.vue.ai/docs/DXM%20Hub/Strategy)

# Quick Start Guide

## Install Vue SDK
You will need your sdk token for initializing your library. You can get your sdk token from [sdk settings](https://support.getblox.ai/docs/cli).

### Using Android Studio
1. If this is your first time using Android Studio, Install Android Studio and dependencies from [Android Studio's Website](https://developer.android.com/studio). And start a new project.
2. Add the JitPack repository to your build file
```kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
3. Add the dependency
```kotlin
dependencies {
    implementation 'com.github.mad.street.den:aar-publishing-msd:1.0.0'
}
```

## Initialize VueSDK

Import and initialize `VueSDK` in `MainActivity`, passing the context, token and baseUrl

```kotlin
import com.msd.vuesdk.helper.client.MSD

class MainActivity {
    ...
    val msdClient = MSD.getInstance(
        context = LocalContext.current,
        token = "YOUR_TOKEN",
        baseUrl = "GIVEN_MSD_BASE_URL"
    )
    ...
}
```

## Discover Events

To ensure accurate and comprehensive event tracking, it is recommended to call the `discoverEvents` function before invoking the track function in your SDK integration. The `discoverEvents` function retrieves the essential information related to track events, such as event names, properties, and default properties. This step allows you to populate the necessary data and configure the event tracking accordingly.


```kotlin
msdClient.discoverEvents(object : DiscoverEventsCallback {
    override fun onDiscoverEventsFetched(response: DiscoverEventsResponse) {
        // Save the response if needed
    }

    override fun onError(errorResponse: JSONObject) {
        // Handle failure of discover events
    }
})
```

## Track Event

To track custom events using our SDK, you can utilize the `track` function. This function allows you to capture specific actions or interactions within your application and gather valuable data for analysis.

Here's an example of how to use the track function:
```kotlin
msdClient.track(
    eventName = "Event_Name",
    properties = <JSON_Object>,
    correlationID = "UNIQUE_CORRELATION_ID"
)
```
**Note:** The `correlationId` is an optional parameter that allows you to provide a unique correlation ID for the event. It is important to ensure that the `correlationId` is unique across each page for both search and track API calls.

The SDK automatically includes several properties when tracking events, eliminating the need for users to manually add them. These properties are essential for comprehensive event tracking and provide valuable insights into user interactions. Here are some of the properties that are automatically added by the SDK:


<!-- TABLE_GENERATE_START -->

| key         | Description                            | Example Value                        |
| ----------- | -------------------------------------- | ------------------------------------ |
| `blox_uuid` | Device UUID generated                  | 5fbeac07-f385-4145-a690-e98571ae985e |
| `platform`  | Platform of the user                   | ios                                  |
| `medium`    | Medium from where requests are sent    | application                          |
| `referrer`  | same values as platform for mobile app | ios                                  |
| `user_id`   | user id passed while calling setUser   | 81bf1152-ce89-4954-b38e-f81875258f6e |
| `url`       | Bundle id of the application           | com.example.myapp                    |

<!-- TABLE_GENERATE_END -->

## Get Recommendations

The getRecommendation functions in the SDK allows you to retrieve recommendations based on specific search criteria and properties. This function provides a convenient way to fetch recommendations and receive the results asynchronously.

### Get Recommendations by Page
```kotlin
 msdClient.getRecommendationsByPage(
    pageReference = "YOUR_PAGE_NAME",
    properties = RecommendationRequest(
        catalogs = <JSON_Object>
    ),
    correlationID = "UNIQUE_CORRELATION_ID",
    object : RecommendationCallback {
        override fun onRecommendationsFetched(response: JSONArray) {
            // Action on recommendations fetched
        }

        override fun onError(errorResponse: JSONObject) {
            // Action on fetch failure
        }
    })

```

### Get Recommendations by Module

```kotlin
 msdClient.getRecommendationsByModule(
    moduleReference = "YOUR_MODULE_NAME",
    properties = RecommendationRequest(
        catalogs = <JSON_Object>
    ),
    correlationID = "UNIQUE_CORRELATION_ID",
    object : RecommendationCallback {
        override fun onRecommendationsFetched(response: JSONArray) {
            // Action on recommendations fetched
        }

        override fun onError(errorResponse: JSONObject) {
            // Action on fetch failure
        }
    })

```

### Get Recommendations by Strategy
```kotlin
msdClient.getRecommendationsByStrategy(
    strategyReference = "YOUR_STRATEGY_NAME",
    properties = RecommendationRequest(
        catalogs = <JSON_Object>
    ),
    correlationID = "UNIQUE_CORRELATION_ID",
    object : RecommendationCallback {
        override fun onRecommendationsFetched(response: JSONArray) {
            // Action on recommendations fetched
        }

        override fun onError(errorResponse: JSONObject) {
            // Action on fetch failure
        }
    })

```
**Note:** The `correlationId` is an optional parameter that allows you to provide a unique correlation ID for the search request. It is important to ensure that the `correlationId` is unique across each page for both search and track API calls.


The SDK automatically includes several properties when tracking events, eliminating the need for users to manually add them. Here are some of the properties that are automatically added by the SDK:

<!-- TABLE_GENERATE_START -->

| key         | Description                            | Example Value                        |
| ----------- | -------------------------------------- | ------------------------------------ |
| `blox_uuid` | Device UUID generated                  | 5fbeac07-f385-4145-a690-e98571ae985e |
| `platform`  | Platform of the user                   | ios                                  |
| `medium`    | Medium from where requests are sent    | application                          |
| `referrer`  | same values as platform for mobile app | ios                                  |
| `user_id`   | user id passed while calling setUser   | 81bf1152-ce89-4954-b38e-f81875258f6e |
| `url`       | Bundle id of the application           | com.example.myapp                    |

<!-- TABLE_GENERATE_END -->

## Set User

The `setUser` function in the SDK allows you to associate a user ID with subsequent API calls after the user has logged in. This user ID is used to track user-specific events and behaviors, providing personalized experiences and accurate analytics.

```kotlin
 msdClient.setUser(userId = "YOUR_USER_ID")
```

## Reset User

The `resetUser` function in the SDK allows you to clear the user information, we recommend you to call this function on user logout/switch. This ensures that any user-specific data and tracking are cleared and no longer associated with the user.

```kotlin
 msdClient.resetUser()
```

## VueSDK debugging and logging

The SDK provides internal logging capabilities for debugging purposes. By default, the logging feature is disabled. 

To enable internal logging, pass `true` to the function `setLogging`:

```kotlin
msdClient.setLogging(loggingState = true)
```

## Complete Code Example
Here's a runnable code example that covers everything in this quick start guide.
```kotlin
import com.msd.vuesdk.helper.client.MSD

class MainActivity {
    ...
    
    let msdClient =  MSD.getInstance(
        context = LocalContext.current,
        token = "YOUR_TOKEN",
        baseUrl = "https://api.msd.com"
    );

    msdClient.track(
        eventName = "YOUR_CUSTOM_EVENT_NAME",
        properties = JSONObject()
    );

    msdClient.getRecommendationsByPage(
        pageReference = "YOUR_PAGE_NAME",
        properties = RecommendationRequest(
            catalogs = JSONObject()
        ),
        correlationID = "UNIQUE_CORRELATION_ID"
        object : RecommendationCallback {
            override fun onRecommendationsFetched(response: JSONArray) {
                // Action on recommendations fetched
            }

            override fun onError(errorResponse: JSONObject) {
                // Action on fetch failure
            }
        })
    ...
}
```


## I want to know more!

Have any questions? Reach out to MSD [Support](https://support.getblox.ai/docs/cli) to speak to someone smart, quickly.