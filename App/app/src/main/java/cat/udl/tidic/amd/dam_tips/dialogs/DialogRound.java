package cat.udl.tidic.amd.dam_tips.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.GameViewModel;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.views.PlayActivity;

public class DialogRound extends DialogFragment {


    public View rootView;
    private FragmentActivity activity;
    private GameViewModel gameViewModel;
    public Question question;
    private Button r1;
    private Button r2;
    private Button r3;
    private Button r4;
    private TextView pregunta;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    public static DialogRound newInstance(FragmentActivity activity, GameViewModel viewModel, Question q){
        DialogRound dialog = new DialogRound();
        Bundle args = new Bundle();
        dialog.activity = activity;
        dialog.gameViewModel = viewModel;
        dialog.setQuestion(q);
        return dialog;
    }
    public void setQuestion(Question q){
        this.question = q;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initView();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder.setView(rootView)
                .setCancelable(false)
                .create();

        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    private void initView() {



        rootView = LayoutInflater.from
                (getContext()).inflate(R.layout.dialog_round, null,false);
        pregunta = rootView.findViewById(R.id.pregunta);
        r1 = rootView.findViewById(R.id.a1);
        r2 = rootView.findViewById(R.id.a2);
        r3 = rootView.findViewById(R.id.a3);
        r4 = rootView.findViewById(R.id.a4);

        t1 = rootView.findViewById(R.id.t1);
        t2 = rootView.findViewById(R.id.t2);
        t3 = rootView.findViewById(R.id.t3);
        t4 = rootView.findViewById(R.id.t4);

        pregunta.setText(question.getQuestion());

        t1.setText(question.getAnswers().get(0).getAnswer());
        t2.setText(question.getAnswers().get(1).getAnswer());
        t3.setText(question.getAnswers().get(2).getAnswer());
        t4.setText(question.getAnswers().get(3).getAnswer());

        //Que posicion del array de respuestas coincide con cada boton

        r1.setTag(new Integer(0));
        r2.setTag(new Integer(1));
        r3.setTag(new Integer(2));
        r4.setTag(new Integer(3));

        View.OnClickListener onClickListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int opcion = (int) v.getTag();
                boolean resp_correcta = question.getAnswers().get(opcion).isIs_correct();
                Log.d("DialogRound", "Has clicado el boton con tag: " + v.getTag() + " y la respeusta es correcta?: " + resp_correcta);
                if(resp_correcta){
                    //pasar categoria a la activity
                    ((PlayActivity)getActivity()).respuestaBack.setText(question.getCategory());
                    dismiss();

                }
                else{
                    ((PlayActivity)getActivity()).respuestaBack.setText("-1");
                    dismiss();
                }
            }
        };
        r1.setOnClickListener(onClickListener);
        r2.setOnClickListener(onClickListener);
        r3.setOnClickListener(onClickListener);
        r4.setOnClickListener(onClickListener);
        /*
        recyclerView_generes = rootView.findViewById(R.id.recyclerView_generes);
        add_generes = rootView.findViewById(R.id.imageView_addGenere);
        */

    }
}
