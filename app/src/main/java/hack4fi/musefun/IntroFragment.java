package hack4fi.musefun;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IntroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class IntroFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment IntroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntroFragment newInstance() {
        IntroFragment fragment = new IntroFragment();
        return fragment;
    }
    public IntroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_intro, container, false);

        //final YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();
//
//        youTubePlayerFragment.initialize(DeveloperKey.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b) {
//                    youTubePlayer.loadVideo("2zNSgSzhBfM");
//                    youTubePlayer.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });

        final Button button = (Button) view.findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).nextPage();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
