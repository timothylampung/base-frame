export interface AuthState {
    isAuthenticated: boolean;
    isLoading: boolean;
    accessToken: string;
}

export const initialState: AuthState = {
    isAuthenticated: false,
    isLoading: false,
    accessToken: null
};
