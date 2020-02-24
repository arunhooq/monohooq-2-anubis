

data "template_file" "function-beat" {

    template = "${file("${path.module}/template/functionbeat.tpl")}"
    
    vars = {
        function-s3bucket           = var.s3_bucket,
        function-service-name       = var.service_name,
        function-environment        = var.environment,
        function-log-group          = var.log_group,
        function-cloudid            = var.cloud_id,
        function-elastic-username   = var.elastic_username,
        function-elastic-password   = var.elastic_password,
        function-policy-name        = var.policy_name,
        function-tags               = jsonencode(var.tags)
        function-format             = "%%{+yyyy.MM.dd}"
    }

}


resource "null_resource" "render-template" {

    depends_on = ["data.template_file.function-beat"]

    triggers = {
       template_rendered = "${data.template_file.function-beat.rendered}"
    }

    provisioner "local-exec" {
      command = "echo '${ data.template_file.function-beat.rendered }' > ./functionbeat.yml"
    }

}

