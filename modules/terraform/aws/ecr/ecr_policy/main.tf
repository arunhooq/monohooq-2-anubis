resource "aws_ecr_repository_policy" "main" {
  repository = var.ecr_name

  policy = <<POLICY
{
  "Version": "2008-10-17",
  "Statement": [
    {
      "Sid": "AllowCrossAccountPull",
      "Effect": "Allow",
      "Principal": {
        "AWS": [
          "arn:aws:iam::906191743243:root",
          "arn:aws:iam::064976301468:root",
          "arn:aws:iam::483024684374:root",
          "arn:aws:iam::782273850218:root"
        ]
      },
      "Action": [
        "ecr:BatchCheckLayerAvailability",
        "ecr:BatchGetImage",
        "ecr:GetDownloadUrlForLayer"
      ]
    }
  ]
}
POLICY
}

