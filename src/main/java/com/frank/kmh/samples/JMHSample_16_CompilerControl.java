package com.frank.kmh.samples;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JMHSample_16_CompilerControl {

    /*
     * We can use HotSpot-specific functionality to tell the compiler what
     * do we want to do with particular methods. To demonstrate the effects,
     * we end up with 3 methods in this sample.
     */

    /**
     * These are our targets:
     *   - first method is prohibited from inlining
     *   - second method is forced to inline
     *   - third method is prohibited from compiling
     *
     * We might even place the annotations directly to the benchmarked
     * methods, but this expresses the intent more clearly.
     */

    public void target_blank() {
        // this method was intentionally left blank
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void target_dontInline() {
        // this method was intentionally left blank
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    public void target_inline() {
        // this method was intentionally left blank
    }

    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    public void target_exclude() {
        // this method was intentionally left blank
    }

    /*
     * These method measures the calls performance.
     */

    @Benchmark
    public void baseline() {
        // this method was intentionally left blank
    }

    @Benchmark
    public void blank() {
        target_blank();
    }

    @Benchmark
    public void dontinline() {
        target_dontInline();
    }

    @Benchmark
    public void inline() {
        target_inline();
    }

    @Benchmark
    public void exclude() {
        target_exclude();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_16_CompilerControl.class.getSimpleName())
                .warmupIterations(0)
                .forks(1)
                .measurementIterations(2).measurementTime(TimeValue.milliseconds(50))
                .build();

        new Runner(opt).run();
    }

}
