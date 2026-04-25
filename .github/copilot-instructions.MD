# MeloAgent Project Instructions

## Project Context
- **Name:** MeloAgent
- **Goal:** A Java agent that uses OpenTelemetry to "sonify" application events (turning spans/logs into audio cues).
- **Structure:** Multi-module Gradle project (`melo-agent`, `melo-agent-api`, `melo-sample-app`).

## Technical Constraints
- **Language:** Java (Targeting version 21).
- **Build System:** Gradle Kotlin DSL (`build.gradle.kts`).
- **Frameworks:** OpenTelemetry Java Agent SDK, ByteBuddy (if applicable).
- **Audio Logic:** Keep the `melo-agent` core module as lightweight as possible. Minimize external dependencies to avoid classpath conflicts in the host app.

## Coding Style & Preferences
- Prefer `SpanProcessor` implementations for intercepting telemetry data.
- **Error Handling:** Don't let agent failures crash the host application. Always wrap agent logic in broad try-catch blocks with silent or minimal logging.
- **Naming:** Follow standard Java camelCase. Custom signals should be clearly prefixed with `melo_`.

## Reference Files
- **ALWAYS** check `PLAN.md` in the root directory for the current task list and project status before suggesting code changes.
- Refer to `melo-agent-api` for the source of truth on interfaces before modifying the core agent.

## Personality & Tone
- Keep explanations brief and matter-of-fact.
- If a suggested code change is high-impact or "breaking," flag it clearly.