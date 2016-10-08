package body AdaptiveQuad is
     function SimpsonsRule(a, b: float) return float is
        c : float := 0.0;
        h3: float := 0.0;
     begin
        c := (a+b)/2.0;
        h3 := (abs(b-a))/6.0;
        return h3*(MyF(a)+(4.0*MyF(c))+MyF(b));
     end SimpsonsRule;

    function RecAQuad(a, b, eps, whole: Float) return float is
        c: float;
        left: float;
        right: float;
        leftQuad: float;
        rightQuad: float;

        procedure CheckRec is
            task t1;
            task t2;
            task body t1 is
            begin
                leftQuad := RecAQuad(a, c, eps/2.0, left);
            end t1;
            task body t2 is
            begin
                rightQuad := RecAQuad(c, b, eps/2.0, right);
            end t2;
        begin
            null;
        end CheckRec;

    begin
        c:= (a+b)/2.0;
        left := SimpsonsRule(a, c);
        right := SimpsonsRule(c, b);

        if (abs(left + right - whole)) <= 15.0*eps then
            return left + right + (left + right - whole)/15.0;
        end if;
        CheckRec;
        return leftQuad+rightQuad;

    end RecAQuad;

    function AQuad(a, b, eps: float) return float is
    begin
        return RecAQuad(a, b, eps, SimpsonsRule(a, b));
    end AQuad;

end AdaptiveQuad;
