generic
    -- function F declared here as the generic parameter.
    -- type T is limited private;
    -- with function MyF(X: T) return T;
    with function MyF(X: float) return float;
    package AdaptiveQuad is
        function AQuad(a, b, eps: Float) return float;
        function RecAQuad(a, b, eps, whole: Float) return float;
        function SimpsonsRule(a, b: float) return float;
end AdaptiveQuad;


