package test.andranik.apiclientdemo.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import test.andranik.apiclientdemo.R;

/**
 * Created by andranik on 10/6/16.
 */

public class DialogUtils {
    public static ProgressDialog showProgressDialog(Activity activity, String message, boolean cancalable){
        ProgressDialog progress = new ProgressDialog(activity);
        progress.setMessage(message);
        progress.setCancelable(cancalable);
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        return progress;
    }

    public static void showOfflineDialog(Activity activity){
        showOfflineDialog(activity, false);
    }

    public static AlertDialog showOfflineDialog(final Activity activity, final boolean finishActivity){
        return DialogUtils.showAlertDialog(activity,
                R.string.no_internet_connection,
                (dialog, which) -> {
                    if (finishActivity) {
                        activity.onBackPressed();
                    }
                });
    }

    public static AlertDialog showSomethingWentWrong(final Activity activity){
        return showSomethingWentWrong(activity, false);
    }

    public static AlertDialog showSomethingWentWrong(final Activity activity, final boolean finishActivity){
        return DialogUtils.showAlertDialog(activity,
                R.string.something_went_wrong,
                (dialog, which) -> {
                    if (finishActivity) {
                        activity.onBackPressed();
                    }
                });
    }

    public static AlertDialog showTimeOutDialog(Activity activity){
        return showAlertDialog(activity, R.string.dialog_timeout_error);
    }

    public static AlertDialog showAlertDialog(Activity activity, int msgId){
        return showAlertDialog(activity, activity.getString(msgId));
    }

    public static AlertDialog showAlertDialog(Activity activity, String msg){
        return showAlertDialog(activity,
                0,
                null,
                msg,
                activity.getString(R.string.ok),
                null,
                null,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, String msg, int posBtnTextId, int negBtnTextId, DialogInterface.OnClickListener posBtnClickListener){
        return showAlertDialog(activity,
                0,
                null,
                msg,
                activity.getString(posBtnTextId),
                activity.getString(negBtnTextId),
                posBtnClickListener,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, @StringRes int titleRes, @StringRes int msgRes, @StringRes int posBtnTextId, @StringRes int negBtnTextId, DialogInterface.OnClickListener posBtnClickListener){
        return showAlertDialog(activity,
                0,
                activity.getString(titleRes),
                activity.getString(msgRes),
                activity.getString(posBtnTextId),
                activity.getString(negBtnTextId),
                posBtnClickListener,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, @StringRes int titleRes, @StringRes int msgRes, @StringRes int posBtnTextId){
        return showAlertDialog(activity,
                0,
                activity.getString(titleRes),
                activity.getString(msgRes),
                activity.getString(posBtnTextId),
                null,
                null,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, String title, String msg, int posBtnTextId, int negBtnTextId, DialogInterface.OnClickListener posBtnClickListener){
        return showAlertDialog(activity,
                0,
                title,
                msg,
                activity.getString(posBtnTextId),
                activity.getString(negBtnTextId),
                posBtnClickListener,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, int msgId, DialogInterface.OnClickListener posBtnClickListener){

        return showAlertDialog(activity,
                0,
                null,
                activity.getString(msgId),
                activity.getString(R.string.ok),
                null,
                posBtnClickListener,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, String title, String msg){
        return showAlertDialog(activity,
                0,
                title,
                msg,
                activity.getString(R.string.ok),
                null,
                null,
                null);
    }

    public static AlertDialog showAlertDialog(Activity activity, int resId, String title, String massage, String posBtnText, String negBtnText, DialogInterface.OnClickListener posBtnClickListener, DialogInterface.OnClickListener negBtnClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(massage);
        builder.setCancelable(false);
        if(title != null){ builder.setTitle(title); }
        if (posBtnText != null){ builder.setPositiveButton(posBtnText, posBtnClickListener); }
        if (negBtnText != null){ builder.setNegativeButton(negBtnText, negBtnClickListener); }

        AlertDialog alert = builder.create();

        if(!activity.isDestroyed() && !activity.isFinishing()) {
            alert.show();
        }

        return alert;
    }
}
