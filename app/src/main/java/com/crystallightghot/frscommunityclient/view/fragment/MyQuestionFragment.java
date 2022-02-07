package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.dialog.CategoryDialogFragment;
import com.crystallightghot.frscommunityclient.view.message.TransportDataMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyQuestionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddPackage)
    TextView btnAddPackage;
    @BindView(R.id.rvMyQuestions)
    RecyclerView rvMyQuestions;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private String mParam1;
    DialogFragment dialogFragment;

    public MyQuestionFragment() {
        dialogFragment = new CategoryDialogFragment();
        FRSCEventBusUtil.register(this);
        // Required empty public constructor
    }

    public static MyQuestionFragment newInstance(String param1) {
        MyQuestionFragment fragment = new MyQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_question, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }


    private void init() {
//        MyClassificationRecycleViewAdapter adapter = new MyClassificationRecycleViewAdapter();
//        rvMyQuestions.setAdapter(adapter);
    }

    @OnClick({R.id.btnBack, R.id.btnAddPackage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                break;
            case R.id.btnAddPackage:
                dialogFragment.show(getParentFragmentManager(),"ff");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(TransportDataMessage message) {
        if (message.getMessageKey() != dialogFragment) {
            return;
        }

        Map data = (Map) message.getData();
        String categoryName = (String) data.get("categoryName");
        String description = (String) data.get("description");
    }

}