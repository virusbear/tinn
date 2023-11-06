package com.virusbear.tinn.opengl

import com.virusbear.tinn.async.ExecutorServiceScheduler
import com.virusbear.tinn.async.Scheduler
import java.util.concurrent.Executors

internal fun createGlScheduler(): Scheduler =
    ExecutorServiceScheduler(Executors.newSingleThreadExecutor())