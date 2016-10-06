-- with Ada.Numerics.Elementary_Functions;
-- use Ada.Numerics.Elementary_Functions;
package body AdaptiveQuad is
     function SimpsonsRule(a, b: float) return float is
        c : float := 0.0;
        h3: float := 0.0;
     begin
        c := (a + b)/2.0;
        h3 := abs(b-a)/6.0;
        return h3*(MyF(a)+4.0*MyF(c)+MyF(b));
     end SimpsonsRule;

    function RecAQuad(a, b, eps, whole: Float) return float is
        C: float := (a+b)/2.0;
        left: float := SimpsonsRule(a, c);
        right: float := SimpsonsRule(a, c);
        value: float := left + right - whole;
        value2: float := 15.0*eps;
    begin
        if (abs(value)) <= value2 then
            return left + right + (left - right - whole)/15.0;
        end if;
        return RecAQuad(a, c, eps/2.0,left) + RecAQuad(c, b, eps/2.0,right);

    end RecAQuad;

    function AQuad(a, b, eps: float) return float is
    begin
        return RecAQuad(a, b, eps, SimpsonsRule(a, b));
    end AQuad;

end AdaptiveQuad;
