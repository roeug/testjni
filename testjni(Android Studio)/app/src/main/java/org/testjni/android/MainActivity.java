package org.testjni.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	static {
  		System.loadLibrary( "laz_lib"); // Usable loads FIRST !!!
		System.loadLibrary( "cwrappas"); // User loads LAST !!!
	}

	public native int cIsomeFunction(int x);
	public native void cVoidVoid();
	public native int cDouble2Int(double x);
	public native int cIntArrayMultiply(int aMultiplier, int[] iArray);
	public native int cDoubleArrayMultiply(double aMultiplier, double[] dArray);
	public native int cLoadBinLib(int x);
	
/*	not tested in this project
	public native void VsomeFunction(int x);
	public native double DsomeFunction(double x);
	public native int cIntInt(int i);
	public native int cDoubleDouble(double x);
	public native double cInt2Double(int i);
	public native int cIntVarDouble(int i, double x);
	public native double cDouble2Double(double x);
*/

	private Button mButtonClear, mButtonIsomeFunction, mButtonVoidVoidFunction, mButtonDoubleToInt;
	private Button mButtonIntArrayMultiply,mButtonDoubleArrayMultiply;
	private Button mButtonLoadBinLib;
	private int[] mIntArray = new int[300];
	private double[] mDoubleArray = new double[300];
	private TextView mTextView1;
	private int miTemp;
	private double mdTemp;
	private double mdTempOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView1 = (TextView) findViewById(R.id.textView1);

		mButtonClear = (Button) findViewById(R.id.buttonClear);
		mButtonClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String aStr = "" ;
				mTextView1.setText(aStr);
			}
		});

		mButtonIsomeFunction = (Button) findViewById(R.id.buttonIsomeFunction);
		mButtonIsomeFunction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String aStr = "cIsomeFunction returns " + String.format("%5d", cIsomeFunction(5));
				mTextView1.setText(aStr);
			}
		});

		mButtonVoidVoidFunction = (Button) findViewById(R.id.buttonVoidVoidFunction);
		mButtonVoidVoidFunction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cVoidVoid();
				String aStr = "pasVoidVoid - Pascal procedure writes to LogCat";
				mTextView1.setText(aStr);
			}
		});

		mButtonDoubleToInt = (Button) findViewById(R.id.buttonDoubleToInt);
		mButtonDoubleToInt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String aStr = "DoubleToInt - Pascal Foor(3.14) returns " + String.format("%5d", cDouble2Int(3.14));
				mTextView1.setText(aStr);
			}
		});

		mButtonIntArrayMultiply = (Button) findViewById(R.id.buttonIntArrayMultiply);
		mButtonIntArrayMultiply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < 100; i++) {
					mIntArray[i]=i;
				}
				mIntArray[100]=-1;
				int iMultiplier = 3;
				cIntArrayMultiply(iMultiplier, mIntArray);
				String aStr = "cIntArrayMult - Pascal pas_intarraymult returns "; 
				for (int i = 0; i < 100; i++) {
					aStr = aStr +" "+ String.format("%5d", mIntArray[i]);
				}	
				mTextView1.setText(aStr);
			}
		});
		
		mButtonDoubleArrayMultiply = (Button) findViewById(R.id.buttonDoubleArrayMultiply);
		mButtonDoubleArrayMultiply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < 100; i++) {
					mDoubleArray[i]=i*1.0+0.1;
				}
				mDoubleArray[100]=-1.0;
				double dMultiplier = 3.14;
				cDoubleArrayMultiply(dMultiplier, mDoubleArray);
				String aStr = "cDoubleArrayMult - Pascal pas_arraymult returns "; 
				for (int i = 0; i < 100; i++) {
					aStr = aStr +" "+ String.format("%8.6g", mDoubleArray[i]);
				}	
				mTextView1.setText(aStr);
			}
		});
		 
		mButtonLoadBinLib = (Button) findViewById(R.id.buttonLoadBinLib);
		mButtonLoadBinLib.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 
				int i=0;
				String aStr = "cLoadBinLib - Pascal pas_loadbinlib returns " + String.format("%5d",cLoadBinLib(i)); 
				mTextView1.setText(aStr);
			}
		});
	  	
		
		
	}
}
