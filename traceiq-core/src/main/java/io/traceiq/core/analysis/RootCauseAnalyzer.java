package io.traceiq.core.analysis;

import io.traceiq.core.model.AnalysisRequest;
import io.traceiq.core.model.AnalysisResult;

public interface RootCauseAnalyzer {

    AnalysisResult analyze(AnalysisRequest request);
}
