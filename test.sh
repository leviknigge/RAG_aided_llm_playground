curl https://api.openai.com/v1/chat/completions \
-H "Content-Type: application/json" \
-H "Authorization: Bearer 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" \
-d '{"model": "gpt-4o-mini", "prompt": "Say this is a test", "temperature": 0, "max_tokens": 7}'