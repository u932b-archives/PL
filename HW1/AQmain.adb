with Text_Io;
use Text_Io;

with Ada.Float_Text_IO;
use Ada.Float_Text_IO;

with AdaptiveQuad;

-- with Ada.Numerics.Elementary_Functions;
-- use Ada.Numerics.Elementary_Functions;
with Ada.Numerics.Generic_Elementary_Functions;

procedure AQMain is

    package FloatFunctions is new Ada.Numerics.Generic_Elementary_Functions(Float);
    use FloatFunctions;

    Epsilon: Float := 0.000001;

    function MyF(x: Float) return Float is
    begin
        return FloatFunctions.sin(x**2);
    end MyF;

    -- package FloatAQ is new AdaptiveQuad(Float, MyF);
    package FloatAQ is new AdaptiveQuad(MyF);

    task ReadPairs is
        -- ENTRY reader (a,b: Natural);
        -- ENTRY start;
    end ReadPairs;

    task ComputeArea is
    --     entry receiver (a,b: float);
        entry start(a,b:float);
        entry die;
    end ComputeArea;

    task PrintResult is
        entry start2(a, b, result:float);
        entry die;
    end PrintResult;

    task body ReadPairs is
        -- ∗ Read in an A value and a B value (using the Get procedure)
        -- ∗ Provide the A and B values to the ComputeArea task (below), so it can perform adapture quadrature.
       -- Input_File: File_Type;
       a,b: float;
       loopCount: Natural:=5;
    begin
        -- Text_IO.Open (File => Input_File, Mode => In_File, Name => "input.txt");
    while not End_Of_Line loop
        -- for I in 1 .. loopCount loop
            Ada.Float_Text_IO.Get (a);
            Ada.Float_Text_IO.Get (b);
            ComputeArea.start(a, b);
       -- end loop;
       -- Close (File => Input_File);
    end loop;
    ComputeArea.die;
    end ReadPairs;

    -- task type ComputeArea is
    task body ComputeArea is
        -- aa, bb, AQ_result: float;
        AQ_result: float;
        death_flag: boolean:=True;
    begin
    --     accept receiver (a,b, AQ_result: float) do
        while death_flag loop
        select
        accept die do
            death_flag := False;
        end die;
        or
        accept start(a, b: float) do
        -- put(a);
        AQ_result := FloatAQ.AQuad(a, b, Epsilon);
        -- put (AQ_result);
        PrintResult.start2(a,b,AQ_result);
        -- put (AQ_result);
        end start;
        end select;
        end loop;
        PrintResult.die;
    end;

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
    end;

begin
   -- Put("The area under sin(x^2) for x = 3.0 to 4.0 is: ");  Put(FloatAQ.AQuad(0.0, 1.0, Epsilon));  New_Line;
    -- ReadPairs.start;
    null;
end AQMain;


