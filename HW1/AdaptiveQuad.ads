generic
    -- function F declared here as the generic parameter.
    type T is limited private;
    with function F(A,B: T) return T;
    package AdaptiveQuad is
        -- function AQuad declared here.
        -- function AQuad(A,B: T, Eps: Float) return T
        function SimpsonsRule(X, Y: T) return T;
        -- function RecAQuad(A, B, whole, Eps: T) return T;
        -- function AQuad(A, B, Eps: T) return T;
end AdaptiveQuad;


