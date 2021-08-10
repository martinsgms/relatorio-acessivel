package br.com.martinsgms.relatorioacessivel

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.martinsgms.relatorioacessivel.databinding.FragmentTimePickerBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimePickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimePickerFragment : Fragment() {

    private var _binding : FragmentTimePickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimePickerBinding.inflate(inflater, container, false)

        binding.hora.setOnClickListener {
            Log.d("TimePickerFragment", "aaaaaaaa")
            openTimePicker()
        }

        return binding.root
    }

    private fun openTimePicker() {
        val isSystem24HourFormat = DateFormat.is24HourFormat(requireContext())
        val clockFormat = if (isSystem24HourFormat) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setTitleText("Selecione a hora da atividade")
            .build()

        materialTimePicker.show(childFragmentManager, "TAG")

        materialTimePicker.addOnPositiveButtonClickListener {
            val h = materialTimePicker.hour
            val m = materialTimePicker.minute

            binding.hora.setText("$h:$m")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimePickerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimePickerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}