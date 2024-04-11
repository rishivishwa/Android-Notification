package com.example.notification.notification;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


public class FcmAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context mContext;
    public FcmAsyncTask(Context context){
        mContext = context;
    }

    private void registerFCM() {
        FirebaseInstanceId firebaseInstanceId =   FirebaseInstanceId.getInstance();
        if (firebaseInstanceId!=null) {
            firebaseInstanceId.getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            try {
                                if (task != null && !task.isSuccessful()) {
                                    return;
                                }
                                FirebaseMessaging firebaseMessaging =    FirebaseMessaging.getInstance();
                               if (firebaseMessaging!=null) {
                                   firebaseMessaging.subscribeToTopic("all_users")
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {

                                               }
                                           });

                                   subscripeToTopic();

//                                   if (BuildConfig.DEBUG) {
                                       firebaseMessaging.subscribeToTopic("TESTING_USER")
                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<Void> task) {

                                                   }
                                               });
//                                   }


                               }
                                // Get new Instance ID token
                                // Log and toast
//                                if (BuildConfig.DEBUG) {
                                    String token = task.getResult().getToken();
                                    String msg = token;
                                    Log.d("token_msg ", "token_msg --  " +msg);
//                                }
                            } catch (Exception e) {
                                Toast.makeText(mContext, " Error "+e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }





    @Override
    protected Void doInBackground(Void... voids) {
        try {
            registerFCM();
        }catch (Exception e){}
        return null;
    }


    private void subscripeToTopic() {
        try {
            String topicName = NotificationRemoteConfig.Companion.getTopicValue(mContext.getApplicationContext());
            if (!TextUtils.isEmpty(topicName) && !topicName.equalsIgnoreCase("ALL")) {
                FirebaseMessaging.getInstance().subscribeToTopic(topicName);
            }
        }catch (Exception e){
        }
    }






}
