import {
  Field,
  FieldContent,
  FieldError,
  FieldLabel,
} from "@/components/ui/field.tsx";
import { Input } from "@/components/ui/input.tsx";

interface RegisterFormProps {
  data: { email: string; password: string; confirmPassword: string };
  errors: Partial<
    Record<keyof { email: string; password: string; confirmPassword: string }, string>
  >;
  onChange: (data: { email: string; password: string; confirmPassword: string }) => void;
  isLoading: boolean;
}

export function RegisterForm({
  data,
  errors,
  onChange,
  isLoading,
}: RegisterFormProps) {
  const handleChange = (field: keyof typeof data, value: string) => {
    onChange({ ...data, [field]: value });
  };

  return (
    <>
      <Field className="mb-8">
        <FieldContent>
          <Field>
            <FieldLabel htmlFor="register-email">邮箱</FieldLabel>
            <Input
              id="register-email"
              type="email"
              placeholder="Your Email"
              value={data.email}
              onChange={(e) => handleChange("email", e.target.value)}
              disabled={isLoading}
            />
            <FieldError
              errors={errors.email ? [{ message: errors.email }] : undefined}
            />
          </Field>

          <Field>
            <FieldLabel htmlFor="register-password">密码</FieldLabel>
            <Input
              id="register-password"
              type="password"
              placeholder="******"
              autoComplete="off"
              value={data.password}
              onChange={(e) => handleChange("password", e.target.value)}
              disabled={isLoading}
            />
            <FieldError
              errors={errors.password ? [{ message: errors.password }] : undefined}
            />
          </Field>

          <Field>
            <FieldLabel htmlFor="register-confirm-password">确认密码</FieldLabel>
            <Input
              id="register-confirm-password"
              type="password"
              placeholder="******"
              autoComplete="off"
              value={data.confirmPassword}
              onChange={(e) => handleChange("confirmPassword", e.target.value)}
              disabled={isLoading}
            />
            <FieldError
              errors={
                errors.confirmPassword ? [{ message: errors.confirmPassword }] : undefined
              }
            />
          </Field>
        </FieldContent>
      </Field>
    </>
  );
}
