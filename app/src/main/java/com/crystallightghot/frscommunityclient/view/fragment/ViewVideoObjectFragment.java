package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoView;

/**
 * @Date 2022/1/18
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
// Instances of this class are fragments representing a single
// object in our collection.
public class ViewVideoObjectFragment extends Fragment {

    String path = "http://42.194.211.199/video/";
    int i ;
    VideoView videoView;
    public static final String ARG_OBJECT = "object";

    public ViewVideoObjectFragment(int i) {
        this.i = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_item_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        videoView = view.findViewById(R.id.video);
        videoView.setUrl(path + i + ".mp4");
        StandardVideoController controller = new StandardVideoController(getActivity());
        controller.addDefaultControlComponent("标题", false);
        //使用IjkPlayer解码
        videoView.setPlayerFactory(IjkPlayerFactory.create());
        videoView.setVideoController(controller); //设置控制器
    }

    public void pauseVideo() {
        videoView.pause();
    }
    public void resumeVideo() {
        videoView.resume();
    }
    public void startVideo() {
        videoView.start();
    }
    public void releaseVideo() {
        videoView.release();;
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseVideo();
    }

    @Override
    public void onStop() {
        videoView.release();;
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeVideo();
    }
}
