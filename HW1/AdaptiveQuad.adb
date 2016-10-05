package body AdaptiveQuad is
     -- function SimpsonsRule(A,B: T) return T is
     --        C : float := 0.0;
     -- begin
     --        C := (A + B)/2.0;
     --        return C;
     -- end SimpsonsRule;
     function SimpsonsRule(X, Y: T) return T is
     begin
            return F(X, Y);
     end SimpsonsRule;


     -- function RecAQuad(...) ...
     -- function AQuad(...) ...
end AdaptiveQuad;
