package nu.aron.kataromannumerals;

import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.control.Option;

public class StateMonad<S, A> {

    public final Function1<S, Tuple2<A, S>> runState;

    public StateMonad(Function1<S, Tuple2<A, S>> runState) {
        this.runState = runState;
    }

    public static <S, A> StateMonad<S, A> unit(A a) {
        return new StateMonad<>(s -> new Tuple2<>(a, s));
    }

    public static <S> StateMonad<S, S> get() {
        return new StateMonad<>(s -> new Tuple2<>(s, s));
    }

    public static <S, A> StateMonad<S, A> getState(Function1<S, A> f) {
        return new StateMonad<>(s -> new Tuple2<>(f.apply(s), s));
    }

    public static <S, A> StateMonad<S, A> transition(Function1<S, S> f, A value) {
        return new StateMonad<>(s -> new Tuple2<>(value, f.apply(s)));
    }

    public <B> StateMonad<S, B> flatMap(Function1<A, StateMonad<S, B>> f) {
        return new StateMonad<>(s -> {
            Tuple2<A, S> temp = runState.apply(s);
            return f.apply(temp._1).runState.apply(temp._2);
        });
    }

    public <B> StateMonad<S, B> map(Function1<A, B> f) {
        return flatMap(a -> StateMonad.unit(f.apply(a)));
    }

    public <B, C> StateMonad<S, C> map2(StateMonad<S, B> sb, Function1<A, Function1<B, C>> f) {
        return flatMap(a -> sb.map(b -> f.apply(a).apply(b)));
    }

    public A eval(S s) {
        return runState.apply(s)._1;
    }

    public static <S> StateMonad<S, Option<S>> transition(Function1<S, S> f) {
        return new StateMonad<>(s -> new Tuple2<>(Option.none(), f.apply(s)));
    }

    // public static <S, A> StateMonad<S, List<A>> compose(List<StateMonad<S,
    // A>> fs) {
    // return fs.foldRight(StateMonad.unit(List.<A> empty()), fs -> acc ->
    // fs.map2(acc, a -> b -> b.cons(a)));
    // }

}