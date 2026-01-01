import {
	ThemeTogglerButton,
	type ThemeTogglerButtonProps,
} from '@/components/animate-ui/components/buttons/theme-toggler.tsx';

interface ThemeTogglerButtonDemoProps {
	variant?: ThemeTogglerButtonProps['variant'];
	size?: ThemeTogglerButtonProps['size'];
	direction?: ThemeTogglerButtonProps['direction'];
	system?: boolean;
}

export default function ThemeTogglerButtonDemo({
																								 variant = "outline",
																								 size = "default",
																								 direction = "ltr",
																								 system = false,
																							 }: ThemeTogglerButtonDemoProps) {
	return (
			<ThemeTogglerButton
					variant={variant}
					size={size}
					direction={direction}
					modes={system ? ['light', 'dark', 'system'] : ['light', 'dark']}
			/>
	);
}