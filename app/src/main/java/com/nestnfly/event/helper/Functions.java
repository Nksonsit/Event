package com.nestnfly.event.helper;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.nestnfly.event.R;
import com.nestnfly.event.custom.MDToast;
import com.nestnfly.event.ui.BaseActivity;
import com.nestnfly.event.ui.DashboardActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {

    public static void showToast(Context context, String msg, int type) {
        MDToast.makeText(context, msg, Toast.LENGTH_SHORT, type).show();
    }

    private static OnDialogClick mOnDialogClick;

    public interface OnDialogClick {
        void OnClick(boolean yesNO);
    }

    public static void openYesNoDialog(BaseActivity context, String title, OnDialogClick onDialogClick) {
        mOnDialogClick = onDialogClick;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        mOnDialogClick.OnClick(true);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        mOnDialogClick.OnClick(false);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public static DialogOptionsSelectedListener dialogOptionsSelectedListener = null;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static void fireIntent(BaseActivity activity, Class cls, boolean isNewActivity) {
        Intent i = new Intent(activity, cls);
        activity.startActivity(i);
        /*if (!isNewActivity) {
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }*/
    }

    public static void fireIntent(BaseActivity activity, Intent intent, boolean isNewActivity) {
        activity.startActivity(intent);
        /*if (!isNewActivity) {
            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }*/
    }

    public static void fireIntent(BaseActivity context, boolean isNewActivity) {
        context.finish();
        /*if (!isNewActivity) {
            context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }*/
    }

    public static void fireIntentForResult(BaseActivity context, Class<?> cls, int requestCode, boolean isNewActivity) {

        Intent intent = new Intent(context, cls);
        context.startActivityForResult(intent, requestCode);
        /*if (!isNewActivity) {
            context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }*/
    }

    public static void fireIntentWithClearFlag(BaseActivity context, Class cls, boolean isNewActivity) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        /*if (!isNewActivity) {
            context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }*/

    }

    public static void hideKeyPad(Context context, View view) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static Typeface getRegularFont(Context _context) {
        Typeface tf = Typeface.createFromAsset(_context.getAssets(), "fonts/regular.ttf");
        return tf;
    }

    public static Typeface getBoldFont(Context _context) {
        Typeface tf = Typeface.createFromAsset(_context.getAssets(), "fonts/bold.ttf");
        return tf;
    }

    public static boolean emailValidation(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void openInMap(Context context, double latitude, double longitude, String labelName) {
        String newUri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + labelName + ")";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newUri));
        context.startActivity(intent);
    }

    public static void makePhoneCall(Context context, String callNo) {
        Intent dialIntent = new Intent();
        dialIntent.setAction(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + callNo));
        context.startActivity(dialIntent);
    }

    public static void loadImage(BaseActivity context, String url, final ImageView imageView, final ProgressBar progressBar) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        imageView.setImageResource(R.mipmap.ic_launcher);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);
    }

    public static void loadCircularImage(BaseActivity context, Drawable drawable, final ImageView imageView, final ProgressBar progressBar) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCropTransform()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(drawable)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);
    }

    public static void loadCircularImage(BaseActivity context, int drawable, final ImageView imageView, final ProgressBar progressBar) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCropTransform()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(drawable)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);
    }

    public static void loadCircularImage(BaseActivity context, Bitmap bitmap, final ImageView imageView, final ProgressBar progressBar) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCropTransform()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(bitmap)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);
    }

    public static void loadCircularImage(final Context context, String url, final ImageView imageView, final ProgressBar progressBar) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCropTransform()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);
    }

    public static void loadImage(final Context context, int drawable, final ImageView imageView, final ProgressBar progressBar) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(drawable)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);
    }

    public static void showAlertDialogWithTwoOption(Context mContext, String positiveText, String negativeText, String message, final DialogOptionsSelectedListener dialogOptionsSelectedListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setCancelable(true);

        if (positiveText.trim().length() > 0) {
            builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialogOptionsSelectedListener != null)
                        dialogOptionsSelectedListener.onSelect(true);
                    dialog.dismiss();
                }
            });
        }
        if (negativeText.trim().length() > 0) {
            builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialogOptionsSelectedListener != null)
                        dialogOptionsSelectedListener.onSelect(false);
                    dialog.dismiss();
                }
            });
        }
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.show();
    }

    /*public static void hideStatusBar(BaseActivity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }*/

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    /*@SuppressLint("HardwareIds")
    public static String getDeviceId(BaseActivity context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }*/

    public static String jsonString(Object json) {
        return new Gson().toJson(json);
    }

    public static void logout(BaseActivity context) {
        PrefUtils.setLoggedIn(context, false);
        Functions.fireIntentWithClearFlag(context, DashboardActivity.class, true);
        (context).finish();
    }

    public interface DialogOptionsSelectedListener {
        void onSelect(boolean isYes);
    }

/*    public static DInfo getDeviceInfo(BaseActivity context) {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        int index = Build.VERSION.SDK_INT - 1;

        String[] mapper = new String[]{
                "ANDROID BASE", "ANDROID BASE 1.1", "CUPCAKE", "DONUT",
                "ECLAIR", "ECLAIR_0_1", "ECLAIR_MR1", "FROYO",
                "GINGERBREAD", "GINGERBREAD_MR1", "HONEYCOMB", "HONEYCOMB_MR1",
                "HONEYCOMB_MR2", "ICE_CREAM_SANDWICH", "ICE_CREAM_SANDWICH_MR1", "JELLY_BEAN",
                "JELLY_BEAN", "JELLY_BEAN", "KITKAT", "KITKAT",
                "LOLLIPOOP", "LOLLIPOOP_MR1", "MARSHMALLOW", "NOUGAT",
                "NOUGAT", "OREO", "OREO", "ANDROID P"};
        String osName = index < mapper.length ? mapper[index] : "UNKNOWN_VERSION";
        DInfo deviceInfo = new DInfo();
        deviceInfo.setDeviceId(Functions.getDeviceId(context));
        deviceInfo.setDeviceModel(Functions.getDeviceName());
        deviceInfo.setDeviceToken(PrefUtils.getFCMToken(context));
        deviceInfo.setDeviceType(1);
        deviceInfo.setMobileOS(osName);
        return deviceInfo;
    }*/

    public static String getDeviceName() {
        return Build.MODEL;
    }


    public static void moveFile(File inputFile, File outputFile) {
        Log.e("inputFile", inputFile.getAbsolutePath());
        Log.e("outputFile", outputFile.getAbsolutePath());
        InputStream in = null;
        OutputStream out = null;
        try {

            in = new FileInputStream(inputFile);
            out = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            inputFile.delete();


        } catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }
}
