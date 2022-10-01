package com.example.payzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Objects;

public class BackGroundService extends Worker {

    public BackGroundService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        class SmsReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                int mainMsg = 0, Debitedmount = 0;
                String lol = "", str = "", msg = "";
                String[] words;
                StringBuilder amount = new StringBuilder();
                if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
                    for (SmsMessage SmaMsg : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                        msg = SmaMsg.getMessageBody();
                        words = msg.split(" ");
                        for (String word : words) {
                            if (Objects.equals(word, "debited") || Objects.equals(word, "DEBITED") || Objects.equals(word, "sent") || Objects.equals(word, "Debited")) {
                                for (String NewWord : words) {
                                    if (Objects.equals(NewWord, "INR")) {
                                        mainMsg = msg.indexOf("INR");
//                                For Testing Purporse Igonore it
//                                lol = msg.substring(mainMsg+3);
//                                int charr=msg.charAt(mainMsg+3);
//                                Log.e("TAG", "onRevive: " +charr);
//                                Log.e("TAG", "onRevive: " +lol);
//                                Log.e("TAG", "onRevive: " +"on INR");
                                        for (int i = mainMsg + 4; i < msg.length(); i++) {
                                            int temp = msg.charAt(i);
                                            if (temp >= 46 && temp <= 57 && temp != 47) {
                                                amount.append(msg.charAt(i));
                                            } else if (temp == 32) {
                                                break;
                                            }
                                        }
                                    } else if (NewWord.contains("Rs") || NewWord.contains("RS")) {
                                        mainMsg = msg.indexOf("R", msg.indexOf(word));
//                                For Testing Purporse Igonore it
//                                lol = msg.substring(mainMsg+3);
//                                int charr=msg.charAt(mainMsg+3);
//                                Log.e("TAG", "onRevive: " +charr);
//                                Log.e("TAG", "onRevive: " +lol);
//                                Log.e("TAG", "onRevive: " +"on RS");
                                        for (int i = mainMsg + 3; i < msg.length(); i++) {
                                            int temp = msg.charAt(i);
                                            if (temp >= 46 && temp <= 57 && temp != 47) {
                                                amount.append(msg.charAt(i));
                                            } else if (temp == 32) {
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            str = amount.toString();
                        }
                    }
                }
                if (!str.equals("")) {
                    Debitedmount = Integer.parseInt(str);
                    Log.e("TAG", "Debited Amount: " + Debitedmount);
                }
            }
        }
        return null;
    }
}
