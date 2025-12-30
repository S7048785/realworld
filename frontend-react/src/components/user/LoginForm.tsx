import {
  Field,
  FieldContent,
  FieldError,
  FieldLabel,
} from "@/components/ui/field.tsx";
import { Input } from "@/components/ui/input.tsx";

interface LoginFormProps {
  data: { email: string; password: string };
  errors: Partial<Record<keyof { email: string; password: string }, string>>;
  onChange: (data: { email: string; password: string }) => void;
  isLoading: boolean;
}

export function LoginForm({ data, errors, onChange, isLoading }: LoginFormProps) {
  const handleChange = (field: keyof typeof data, value: string) => {
    onChange({ ...data, [field]: value });
  };

  return (
    <>
      <Field className="mb-8">
        <FieldContent>
          <Field>
            <FieldLabel htmlFor="login-email">邮箱</FieldLabel>
            <Input
              id="login-email"
              type="email"
              placeholder="Enter Your Email"
              value={data.email}
              onChange={(e) => handleChange("email", e.target.value)}
              disabled={isLoading}
            />
            <FieldError errors={errors.email ? [{ message: errors.email }] : undefined} />
          </Field>

          <Field>
            <div className="flex items-center justify-between">
              <FieldLabel htmlFor="login-password">密码</FieldLabel>
              <a
                href="#"
                className="text-sm underline-offset-4 hover:underline"
              >
                忘记了你的密码?
              </a>
            </div>
            <Input
              id="login-password"
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
        </FieldContent>
      </Field>
    </>
  );
}
