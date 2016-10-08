with Text_Io;
use Text_Io;

with Ada.Float_Text_IO;
use Ada.Float_Text_IO;

with AdaptiveQuad;

with Ada.Numerics.Generic_Elementary_Functions;

procedure AQMain is

    package FloatFunctions is new Ada.Numerics.Generic_Elementary_Functions(Float);
    use FloatFunctions;

    Epsilon: Float := 0.000001;

    function MyF(x: Float) return Float is
    begin
        return FloatFunctions.sin(x**2);
    end MyF;

    package FloatAQ is new AdaptiveQuad(MyF);

    task ReadPairs;

    task ComputeArea is
        entry start(a,b:float);
        entry die;
    end ComputeArea;

    task PrintResult is
        entry start2(a, b, result:float);
        entry die;
    end PrintResult;

    task body ReadPairs is
       a,b: float;
    begin
    while not End_Of_Line loop
            Ada.Float_Text_IO.Get (a);
            Ada.Float_Text_IO.Get (b);
            ComputeArea.start(a, b);
    end loop;
    ComputeArea.die;
    end ReadPairs;

    task body ComputeArea is
        AQ_result: float;
        death_flag: boolean:=True;
    begin
        while death_flag loop
        select
        accept die do
            death_flag := False;
        end die;
        or
        accept start(a, b: float) do
        AQ_result := FloatAQ.AQuad(a, b, Epsilon);
        PrintResult.start2(a,b,AQ_result);
        end start;
        end select;
        end loop;
        PrintResult.die;
    end ComputeArea;

    task body PrintResult is
        death_flag: boolean:=True;
    begin
        while death_flag loop
        select
        accept die do
            death_flag := False;
        end die;
        or
        accept start2(a, b, result:float) do
        Put("The area under sin(x^2) for x ="); Put(a); Put(" to"); Put(b); Put(" is:"); Put(result); New_Line;
        end start2;
        end select;
        end loop;
    end PrintResult;

begin
    null;
end AQMain;
