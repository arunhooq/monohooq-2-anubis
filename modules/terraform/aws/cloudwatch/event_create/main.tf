resource "aws_cloudwatch_event_rule" "every_700" {
    name = "every-0700"
    description = "Every morning at 7"
    schedule_expression = var.schedule
}

resource "aws_cloudwatch_event_target" "every_700" {
    rule = aws_cloudwatch_event_rule.every_700.id
    arn = var.lambda
}

resource "aws_lambda_permission" "allow_cloudwatch_to_call_check_foo" {
    statement_id = "AllowExecutionFromCloudWatch"
    action = "lambda:InvokeFunction"
    function_name = var.function
    principal = "events.amazonaws.com"
    source_arn = aws_cloudwatch_event_rule.every_700.arn
}