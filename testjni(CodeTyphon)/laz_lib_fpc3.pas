{$PIC ON}
library laz_lib_fpc3;


{$mode objfpc}{$H+}
uses
 Math, // For Floor
 Interfaces,  // needed for DebugLn to LogCat
 LCLproc, // for DebugLn  to be compiled
 classes, // for TStringList
 SysUtils;

type
 AndroidIntArray = array[0..300] of Longint;    // 300 - any value
 AndroidDoubleArray = array[0..300] of Double;

const
 AndroidDataFileName = '/data/data/org.testjni.android/lib/libbin.so';
var
 tmpInt: Integer;
 tmpDouble: Double;


 function pas_double2double(d1: Double): Double; CDECL; EXPORT;
 begin
  Result := 0.1 * d1;
 end;

 function pas_int2double(int1: Longint): Double; CDECL; EXPORT;
 begin
  DebugLn('in pas_int2double int1=' + IntToStr(int1));
  Result := 6.0 * int1;
 end;

 procedure pas_void_void; CDECL; EXPORT;
 begin
  tmpInt := tmpInt + 1;
  DebugLn('in pas_void_void tmpInt =' + IntToStr(tmpInt));
 end;

 function pas_int_int(int1: Longint): Longint; CDECL; EXPORT;
 begin
  Result := 4 * int1;
 end;

 function pas_intvardouble(int1: Longint; var d1: Double): Longint; CDECL; EXPORT;
 begin
  d1 := 7.0 * int1;
  Result := 10 * int1;
 end;

 function pas_double2int(double_in: Double): Longint; CDECL; EXPORT;
 var
  DoubleTmp: Double;
 begin
  DoubleTmp := double_in;
  Result := floor(doubleTmp);
 end;

 function pas_loadbinlib(int_in: Longint): Longint; CDECL; EXPORT;
 var
  aStringList: TStringList;
 begin
  DebugLn('In pas_loadbinlib int_in=' + IntToStr(int_in));
  if not (FileExists(AndroidDataFileName)) then
  begin
   DebugLn(AndroidDataFileName + ' not found');
   Result:=-2;
   exit;
  end
  else
  // Load your data here
  begin
   try
    aStringList:= TStringList.Create;
    try
     aStringList.LoadFromFile(AndroidDataFileName);
     DebugLn('In pas_loadbinlib aStringList.Text=' + aStringList.Text);
     Result:= Length(aStringList.Text);
    finally
      aStringList.Free;
    end;
   except
    Result:=-1;
   end;
  end;
 end;

 function pas_intarraymult(aMultiplier: Longint; var anIntArray: AndroidIntArray): Longint; CDECL; EXPORT;
 var
  I: Integer;
 begin
  DebugLn('In pas_intarraymult aMultiplier=' + IntToStr(aMultiplier));
  ;
  I := 0;
  while (anIntArray[I] >= 0) do
  begin
   anIntArray[I] := anIntArray[I] * aMultiplier;
   I := I + 1;
  end;
  Result := I;
 end;

 function pas_doublearraymult(dMultiplier: float; var aDoubleArray: AndroidDoubleArray): Longint; CDECL; EXPORT;
 var
  I: Integer;
 begin
  Result := -1;
  try
   I := 0;
   while (aDoubleArray[I] > 0) do
   begin
    aDoubleArray[I] := aDoubleArray[I] * dMultiplier;
    I := I + 1;
   end;
   Result := I;
  except
   Result := -1;
  end;
 end;


exports pas_int_int;
exports pas_void_void;
exports pas_int2double;
exports pas_intvardouble;
exports pas_double2int;
exports pas_double2double;
exports pas_loadbinlib;
exports pas_intarraymult;
exports pas_doublearraymult;


begin
 tmpDouble := 0.0;
 tmpInt := 0;
end.


