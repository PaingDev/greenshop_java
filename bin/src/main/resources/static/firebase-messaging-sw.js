// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts("https://www.gstatic.com/firebasejs/7.15.0/firebase-app.js");
importScripts(
  "https://www.gstatic.com/firebasejs/7.15.0/firebase-messaging.js"
);

firebase.initializeApp({
  apiKey: "AIzaSyBeDWEXM8-jBk68vCeoxPSP9ktuVSS6Pd8",
  authDomain: "freshpoint-99d6c.firebaseapp.com",
  databaseURL: "https://freshpoint-99d6c.firebaseio.com",
  projectId: "freshpoint-99d6c",
  storageBucket: "freshpoint-99d6c.appspot.com",
  messagingSenderId: "1027506294733",
  appId: "1:1027506294733:web:dc11d95bd120cfb6dafe84",
  measurementId: "G-HV5MQXMJ1L",
  gcm_sender_id: "1027506294733",
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();
messaging.usePublicVapidKey(
  "BFxOzL4jlsJ8tx-eIi5fh0tZ3A2VmLtV4NAEHEvTTfbn9o6g97WsikqPrS6UM7wxAm6sGjTkHd97u5gckYUOM4k"
);

// messaging.onMessage((payload) => {
//   console.log("Message received. ", payload);
//   // ...
// });

messaging.setBackgroundMessageHandler(function(payload) {
  console.log(
    "[firebase-messaging-sw.js] Received background message ",
    payload
  );
  const notificationTitle = payload.data.title;
  const notificationOptions = {
    body: payload.data.body,
    priority: payload.data.priority,
    vibrate: payload.data.vibrate,
    icon: payload.data.icon,
    //click_action: payload.data.link,
    //link: payload.data.link,
    badge: payload.data.badge,
    icon: payload.data.icon,
  };

  return self.registration.showNotification(
    notificationTitle,
    notificationOptions
  );
});
