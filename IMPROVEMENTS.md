# Improvements

## 1. Add automated tests and GitHub Actions checks

- **Unit tests** would check the behavior of individual use cases in isolation.
- **Integration tests** would check that use cases work together in more complex, multi-step scenarios.
- **CI/CD** would use GitHub Actions to run unit and integration tests on every push. This is especially useful with the AI-assisted workflow below, since the tests can catch regressions the agent missed.
- **Branch workflow** would keep `main` stable and use `develop` to bring the team's changes together. Developers would branch from `develop` for each feature, merge back after the checks pass, and merge `develop` into `main` once the combined changes are ready.

## 2. Make refresh failures clearer

I would show when the saved data was last updated. If a refresh fails, I would keep that data visible and tell the user what to do next, such as checking their connection or trying again.

## 3. Document the AI-assisted development workflow

I would use the same workflow with Codex or Claude Code. The project instructions (`AGENTS.md` for Codex or `CLAUDE.md` for Claude Code) would describe the app structure, coding rules, and checks to run. Each layer would have a short `SKILL.md` with its own guidance.

- **Presentation** would cover Compose components and previews, ViewModel state, and loading, empty, and error behavior.
- **Data** would cover API models, Room entities, mappers, and preserving the cache when a refresh fails.
- **Domain** would cover use cases, repository contracts, and keeping business logic independent of Android and storage details.

With either tool, the main agent would lead and delegate small, self-contained tasks to one worker when delegation is enabled. The lead would define the scope, review the diff, and verify the result.

## 4. Support dark theme and more languages

I would follow the device's light or dark theme and check that text is readable in both. If users need more languages, I would translate labels and error messages using the app's existing string resources.

## 5. Use feature-based layered architecture

I would give each new feature its own package, with `data`, `domain`, and `presentation` layers where needed, following the existing `stations` feature. Clear feature boundaries make each feature easier to change and test on its own, and let developers work on different features in parallel.

## 6. Consider multiplatform support when choosing libraries

When a new feature needs a library, I would prefer one that supports Kotlin Multiplatform if it meets the feature's needs. This would mean fewer dependencies to replace if we later add iOS support.

## 7. Test prototypes before building new features

I would run a couple of short user testing rounds with a simple prototype before writing code. That helps find confusing steps early, when changing the design is quick and cheap.
