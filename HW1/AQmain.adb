with Text_Io;
use Text_Io;

with Ada.Float_Text_IO;
use Ada.Float_Text_IO;

with AdaptiveQuad;

with Ada.Numerics.Elementary_Functions;
use Ada.Numerics.Elementary_Functions;
procedure AQMain is

  function F(A: Float;B: Float) return Float is
      c:float;
      h3:float;
  begin
     -- return A*B;
      c := A+B/2.0;
      h3 := B-A/6.0;
      return h3*(Sin(a)+4.0*Sin(c)+Sin(b));
  end F;

  -- package FloatAQ is new AdaptiveQuad(Float, F);
  package FloatAQ is new AdaptiveQuad(Float, F);

begin
   Put("The float version produces: ");  Put(FloatAQ.SimpsonsRule(3.0, 4.0));  New_Line;
end AQMain;


