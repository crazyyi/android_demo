package com.google.tsang;

import java.util.HashMap;
import java.util.Map;

import android.app.DialogFragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A dialog fragment designed for gallery display
 * 
 * @author Yi Zeng
 *
 */
public class GalleryDialog extends DialogFragment {
    
    private static final String NO_IMAGES_FOR_THIS_STATE = "No images for this state.";
    private static final String DIALOG_TITLE = "title";
    private static final String DIALOG_CONTENT = "dialogContent";

    private GalleryDialog() {
        // Protected from initializing from constructor
    }
    
    public static GalleryDialog getInstance(int title, String dialogContent) {
        GalleryDialog gDialog = new GalleryDialog();
        final Bundle args = new Bundle();
        args.putInt(DIALOG_TITLE, title);
        args.putString(DIALOG_CONTENT, dialogContent);
        gDialog.setArguments(args);
        gDialog.setCancelable(true);
        return gDialog;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = (View)inflater.inflate(R.layout.gallery_fragment, container, false);
        // Get images from resource
        initImages();
        
        final Gallery gallery = (Gallery) v.findViewById(R.id.gallery);
        final String dialogContent = getArguments().getString(DIALOG_CONTENT);
        final Integer[] images = getImages(dialogContent);
        
        if (images == null) {
            Toast.makeText(getActivity(), NO_IMAGES_FOR_THIS_STATE, Toast.LENGTH_SHORT);
            Log.v("log", NO_IMAGES_FOR_THIS_STATE);
        }
        else {
            gallery.setAdapter(new ImageAdapter(getActivity(), images));
        }
        
        // Set the title for the dialog
        int title = getArguments().getInt(DIALOG_TITLE);
        String titleString = getResources().getString(title);
        getDialog().setTitle(titleString);
        
        Button btnBack = (Button) v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {   
                getDialog().dismiss();
            }
        });
        
        return v;
    }
    
    private Integer[] imageNSW = {
            R.drawable.sydney, 
            R.drawable.blue_mountain,
            R.drawable.bondi,
            R.drawable.south_coast,
            R.drawable.silverton_nsw
    };
    private Integer[] imageVIC = {
            R.drawable.gibson_steps, 
            R.drawable.greate_ocean_road,
            R.drawable.stmarys_church, 
            R.drawable.twelve_apostles
    };
    private Integer[] imageSA = {
            R.drawable.sa1, 
            R.drawable.sa2};
    private Integer[] imageQueensland = {
            R.drawable.queensland1,
            R.drawable.queensland2,
            R.drawable.queensland3
    };
    private Integer[] imageWA = {
            R.drawable.wa,
            R.drawable.wa_rock,
            R.drawable.perth,
            R.drawable.wa3
    };
    private Integer[] imageNT = {
            R.drawable.na1,
            R.drawable.na2,
            R.drawable.na3
    };
    private Integer[] imageTasmania = {
            R.drawable.tasmania1,
            R.drawable.tasmania2,
            R.drawable.tasmania3,
    };
    private Integer[] imageACT = {
            R.drawable.canberra1,
            R.drawable.canberra2
    };
    
    final Map<String, Integer[]> imageMap = new HashMap<String, Integer[]>();
    
    /**
     * Get images array with given content name
     * 
     * @param dialogContent
     * @return
     */
    private Integer[] getImages(String dialogContent) {
        return imageMap.get(dialogContent);
    }
    
    /**
     * Puts related images into a map
     */
    private void initImages() {
        for (String stateName : getResources().getStringArray(R.array.list_content)) {
            if ("New South Wales".equals(stateName)) {
                imageMap.put(stateName, imageNSW);
            }
            else if ("Victoria".equals(stateName)) {
                imageMap.put(stateName, imageVIC);
            }
            else if ("South Australia".equals(stateName)) {
                imageMap.put(stateName, imageSA);
            }
            else if ("Queensland".equals(stateName)) {
                imageMap.put(stateName, imageQueensland);
            }
            else if ("Western Australia".equals(stateName)) {
                imageMap.put(stateName, imageWA);
            }
            else if ("Australian Capital Territory".equals(stateName)) {
                imageMap.put(stateName, imageACT);
            }
            else if ("Northern Territory".equals(stateName)) {
                imageMap.put(stateName, imageNT);
            }
            else if ("Tasmania".equals(stateName)) {
                imageMap.put(stateName, imageTasmania);
            }
        }
    }
    
    // customized ImageAdapter for displaying images of the given state
    private class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;
        
        private Integer[] mImageIds;
        
        public ImageAdapter(Context c, Integer[] imageIds) {
            mContext = c;
            mImageIds = imageIds;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.MyGallery);
            mGalleryItemBackground = attr.getResourceId(
                    R.styleable.MyGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }
        
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(360, 240));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(mGalleryItemBackground);
            
            return imageView;

        }
        
    }
}
